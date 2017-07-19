package com.eshop.service;


import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class DataSourceTest {

    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = DataSource.getInstance();
    }

    @Test
    public void getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
        connection.close();
    }
}