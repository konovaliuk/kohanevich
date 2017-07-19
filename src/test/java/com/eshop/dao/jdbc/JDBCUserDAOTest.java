package com.eshop.dao.jdbc;

import com.eshop.dao.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class JDBCUserDAOTest {

    private JDBCUserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = JDBCUserDAO.getInstance();
    }

    @Test
    public void createAndFindUser(){
        User user = User.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanivanov1@gmail.com")
                .setCash(0d)
                .setPassword("password")
                .setActive(true)
                .build();

        userDAO.addNew(user);
        User user1 = userDAO.findEntity(user.getEmail());
        assertNotNull(user1);
    }

    @Test
    public void findAllUser(){
        List<User> users = userDAO.findAll();
        assertNotNull(users);
    }

    @Test
    public void deleteUser(){
        User user = User.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanivanov2@gmail.com")
                .setCash(0d)
                .setPassword("password")
                .setActive(true)
                .build();

        userDAO.addNew(user);
        userDAO.delete(user.getEmail());
        assertNull(userDAO.findEntity(user.getEmail()));
    }

    @Test
    public void addCashToUser(){
        User user = User.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanivanov3@gmail.com")
                .setCash(0d)
                .setPassword("password")
                .setActive(true)
                .build();

        userDAO.addNew(user);
        int EXAMPLE_INT = 500;
        userDAO.addCash(user.getEmail(), EXAMPLE_INT);
        assertTrue(userDAO.findEntity(user.getEmail()).getCash() == EXAMPLE_INT);
    }

    @Test
    public void withdrawCashFromUser(){
        User user = User.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanivanov4@gmail.com")
                .setCash(500d)
                .setPassword("password")
                .setActive(true)
                .build();
        userDAO.addNew(user);
        userDAO.withdrawCash(user, 500.);
        assertTrue(userDAO.findEntity(user.getEmail()).getCash() == 0);
    }
}
