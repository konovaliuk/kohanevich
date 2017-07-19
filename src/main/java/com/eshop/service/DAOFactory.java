package com.eshop.service;


import com.eshop.dao.jdbc.JDBCOrderDAO;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.dao.jdbc.JDBCUserDAO;

public abstract class DAOFactory {

    public static final int H2 = 1;

    public abstract JDBCProductDAO getProductDAO();

    public abstract JDBCUserDAO getUserDAO();

    public abstract JDBCOrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case H2:
                return new H2DAOFactory();
            default:
                return null;
        }
    }


}
