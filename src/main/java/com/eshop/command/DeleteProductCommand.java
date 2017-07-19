package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.service.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductCommand implements ICommand {

    private static final Logger log = Logger.getLogger(DeleteProductCommand.class);

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCProductDAO productDAO = daoFactory.getProductDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productSeries = request.getParameter("delete");
        Product product = productDAO.findEntity(productSeries);
        productDAO.delete(productSeries);
        productDAO.update(product);

        log.info(product.getModel() + " " + product.getProductType() + " deleted");
        return "/pages/home.jsp";
    }
}
