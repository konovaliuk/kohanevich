package com.eshop.dao;

import com.eshop.dao.entities.Order;
import com.eshop.service.DataSource;

import java.sql.Connection;

/**
 * Data access object for Order table
 *
 * @author Yelisey Kohanevich
 */

public abstract class OrderDAO {

    /**
     * Tries to update order in database
     * @param entity Order
     * @return updated order
     * */
    public abstract Order update(Order entity);

    /**
     * Removes order from order table by orderId
     * @param id orderId of order
     * @return true if removing was successful or false if not
     */
    public abstract boolean delete(Integer id);

    /**
     * Inserts order into database
     * @param entity Order
     * @return true if insertion was successful or false if not
     */
    public abstract boolean addNew(Order entity);

    protected Connection getConnection() {
        try {
            return DataSource.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong! Connection not available.");
        }
    }
}
