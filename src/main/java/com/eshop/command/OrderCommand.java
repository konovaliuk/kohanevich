package com.eshop.command;

import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;
import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCOrderDAO;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.dao.jdbc.JDBCUserDAO;
import com.eshop.service.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderCommand implements ICommand {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCProductDAO productDAO = daoFactory.getProductDAO();
    private JDBCUserDAO userDAO = daoFactory.getUserDAO();
    private JDBCOrderDAO orderDAO = daoFactory.getOrderDAO();

    private static final Logger log = Logger.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Order cart = (Order) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart.getUser().getId() == null) {
            user = userDAO.findEntity(cart.getUser().getEmail());
            cart.setUser(user);
        }


        if(cart.getUser().getCash() < cart.getTotalPrice()){
            return "/pages/error.jsp";
        }
        else {
            boolean result = orderDAO.addNew(cart);
            if (result) {
                user = userDAO.withdrawCash(user, cart.getTotalPrice());
                for (OrderEntry orderEntry : cart.getEntries()) {
                    try {
                        productDAO.sell(orderEntry.getProduct(), orderEntry.getQuantity());
                    } catch (Exception e) {
                        return "/pages/error.jsp";
                    }
                }
                session.setAttribute("user", user);
                session.removeAttribute("cart");
                log.info(user.getEmail() + " bought " + cart.getEntries());
            } else {
                return "/pages/error.jsp";
            }
            return "/pages/confirmationPage.jsp";}

    }
}
