package com.eshop.dao.jdbc;

import com.eshop.dao.entities.Product;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;


public class JDBCProductDAOTest {

    private JDBCProductDAO productDAO;

    @Before
    public void setUp(){
        productDAO = JDBCProductDAO.getInstance();
    }

    @Test
    public void createAndFindProduct(){
        Product product = new Product(null,
                "company",
                "model",
                "series",
                1000.0,
                10,
                "Mobile");
        productDAO.addNew(product);
        Product product1 = productDAO.findEntity(product.getSeries());
        assertNotNull(product1);
    }



    @Test
    public void sellProducts(){
        Product product = new Product(null,
                "company",
                "model",
                "series2",
                1000.0,
                10,
                "Mobile");
        productDAO.addNew(product);
        product.setId(productDAO.findEntity(product.getSeries()).getId());
        final int EXAMPLE = 5;
        productDAO.sell(product, EXAMPLE);
        assertTrue(product.getStock() == EXAMPLE);
    }

    @Test
    public void deleteProductFromDB(){
        Product product = new Product(null,
                "company",
                "model",
                "series3",
                1000.0,
                10,
                "Mobile");
        productDAO.addNew(product);
        productDAO.delete(product.getSeries());
        assertNull(productDAO.findEntity(product.getSeries()));
    }
}
