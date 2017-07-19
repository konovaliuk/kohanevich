package com.eshop.command;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;
import com.eshop.service.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminCommand implements ICommand {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCUserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String emailToBlock = request.getParameter("blockUser");
        if (emailToBlock != null) {
            User user = userDAO.findEntity(emailToBlock);
            user.setActive(false);
            userDAO.update(user);
            page = "/pages/admin.jsp";

        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null && user.isAdmin()) {
                List<User> userList; //if null create

                userList = userDAO.findAll();
                session.setAttribute("userList", userList);
                page = "/pages/admin.jsp";

            } else {
                page = "/pages/error.jsp";
            }
        }
        return page;
    }
}
