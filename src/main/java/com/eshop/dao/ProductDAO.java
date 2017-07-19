package com.eshop.dao;

import com.eshop.dao.entities.Product;
import com.eshop.service.DataSource;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for Product table
 *
 * @author Yelisey Kohanevich
 */

public abstract class ProductDAO {

    /**
     * Finds product by series
     * @param series String
     * @return product if exists
     */
    public abstract Product findEntity(String series);

    /**
     * Inserts product into database
     * @param product Product
     * @return true if insertion was successful or false if not
     */
    public abstract boolean addNew(Product product);

    /**
     * Tries to update product in database
     * @param product Product
     * @return updated product
     */
    public abstract Product update(Product product);

    /**
     * Removes product by series
     * @param series String
     * @return true if removing was successful or false if not
     */
    public abstract boolean delete(String series);

    /**
     * Finds specified product
     * @param productType String
     * @param start position for LIMIT
     * @param count position for LIMIT (for pagination)
     * @return
     */
    public abstract List<Product> findSpecifiedProduct(String productType, int start, int count);

    /**
     * Reduces the number of product in stock
     * @param product Product
     * @param boughtItemsAmount int
     * @throws Exception
     */
    public abstract void sell(Product product, int boughtItemsAmount) throws Exception;

    protected Connection getConnection() {
        try {
            return DataSource.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong! Connection not available.");
        }
    }
}
