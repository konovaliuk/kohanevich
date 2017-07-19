package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.service.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PhonesCommand implements ICommand {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCProductDAO productDAO = daoFactory.getProductDAO();

    private static final int TOTAL = 5;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringPageId = request.getParameter("page");

        if (stringPageId != null) {
            int pageId = Integer.parseInt(stringPageId);

            if (pageId == 1) {
            } else {
                pageId = pageId - 1;
                pageId = pageId * TOTAL + 1;
            }
            List<Product> phones = productDAO.findSpecifiedProduct("'Mobile'", pageId, TOTAL);
            request.setAttribute("phones", phones);
        }
        return "/pages/phones.jsp";
    }
}
