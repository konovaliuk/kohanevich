package com.eshop.command;

import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;
import com.eshop.dao.entities.Product;
import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCOrderDAO;
import com.eshop.dao.jdbc.JDBCProductDAO;
import com.eshop.service.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCommand implements ICommand {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
    private JDBCProductDAO productDAO = daoFactory.getProductDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        Order cart = (Order) session.getAttribute("cart");
        String productSeries = request.getParameter("addToBucket");
        String cancelOrder = request.getParameter("cancelOrder");
        String quantityParameter = request.getParameter("quantity");
        Integer quantity = null;
        if (quantityParameter != null){
            quantity = Integer.parseInt(quantityParameter);
        }
        if (productSeries == null) {
            if(cancelOrder != null){
                cart = null;
                session.setAttribute("cart", cart);
            }
            return "/pages/bucket.jsp";

        } else {
            Product product = productDAO.findEntity(productSeries);

            @SuppressWarnings("unchecked")

            User user = (User) session.getAttribute("user");
            if (cart == null) {
                cart = new Order();
                OrderEntry entry = new OrderEntry();
                entry.setPrice(product.getPrice());
                entry.setProduct(product);
                entry.setQuantity(quantity);
                List<OrderEntry> entries = new ArrayList<>();
                entries.add(entry);
                cart.setUser(user);
                cart.setTotalPrice(product.getPrice() * quantity);
                cart.setEntries(entries);
                session.setAttribute("cart", cart);
            } else {
                List<OrderEntry> entries = cart.getEntries();
                Optional<OrderEntry> any = entries.stream().filter(i -> i.getProduct().getId().equals(product.getId())).findAny();
                if (any.isPresent()) {
                    OrderEntry entry = any.get();
                    entry.setQuantity(entry.getQuantity() + quantity);
                    entry.setPrice(entry.getPrice() * entry.getQuantity());
                } else {
                    OrderEntry entry = new OrderEntry();
                    entry.setPrice(product.getPrice());
                    entry.setProduct(product);
                    entry.setQuantity(quantity);
                    entries.add(entry);
                }
                cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);
            }
            return "/pages/phones.jsp";
        }
    }
}
