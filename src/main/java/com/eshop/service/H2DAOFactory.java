package com.eshop.service;

import com.eshop.dao.jdbc.JDBCOrderDAO;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.dao.jdbc.JDBCUserDAO;

public class H2DAOFactory extends DAOFactory {
    @Override
    public JDBCProductDAO getProductDAO() {
        return JDBCProductDAO.getInstance();
    }

    @Override
    public JDBCUserDAO getUserDAO() {
        return JDBCUserDAO.getInstance();
    }

    @Override
    public JDBCOrderDAO getOrderDAO() {
        return JDBCOrderDAO.getInstance();
    }
}
