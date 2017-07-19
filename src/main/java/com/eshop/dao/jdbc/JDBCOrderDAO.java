package com.eshop.dao.jdbc;

import com.eshop.dao.OrderDAO;
import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCOrderDAO extends OrderDAO{

    private static final Logger log = Logger.getLogger(JDBCOrderDAO.class);

    private static volatile JDBCOrderDAO instance;

    private JDBCOrderDAO() {
    }

    public static JDBCOrderDAO getInstance() {
        if (instance == null) {
            synchronized (JDBCOrderDAO.class) {
                if (instance == null)
                    instance = new JDBCOrderDAO();
            }
        }
        return instance;
    }

    @Override
    public Order update(Order entity) {
        final String SQL = "UPDATE Orders SET "
                + " userId=? WHERE totalPrice=?";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, entity.getUser().getId());
            statement.setDouble(2, entity.getTotalPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        final String SQL = "DELETE FROM Orders WHERE orderId=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean addNew(Order entity) {
        final String SQL_ORDER = "INSERT INTO Orders(userId, totalPrice)" +
                "VALUES (?, ?) ";

        final String SQL_ENTRY = "INSERT INTO Order_Entry(orderId, productId, price, quantity)" +
                "VALUES (?, ?, ?, ?)";

        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ORDER);
             PreparedStatement statement = connection.prepareStatement(SQL_ENTRY)) {


            connection.setAutoCommit(false);

            preparedStatement.setInt(1, entity.getUser().getId());
            preparedStatement.setDouble(2, entity.getTotalPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int orderId;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            for (OrderEntry entry : entity.getEntries()) {
                statement.setInt(1, orderId);
                statement.setInt(2, entry.getProduct().getId());
                statement.setDouble(3, entry.getPrice());
                statement.setInt(4, entry.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            ex.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
