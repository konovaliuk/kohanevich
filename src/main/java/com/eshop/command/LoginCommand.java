package com.eshop.command;


import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;
import com.eshop.service.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ICommand {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCUserDAO userDAO = daoFactory.getUserDAO();

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static final String LOGIN = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = userDAO.findEntity(login);


        if (user != null && user.getPassword().equals(password) && user.isActive()){
            page = "/pages/home.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            log.info(user.getEmail() + " logged in");
        }
        else {
            page = "/pages/error.jsp";
        }
        return page;
    }
}
