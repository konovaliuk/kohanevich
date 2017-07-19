package com.eshop.dao;

import com.eshop.dao.entities.User;
import com.eshop.service.DataSource;

import java.sql.Connection;
import java.util.List;

/**
 * Data access object for User table
 *
 * @author Yelisey Kohanevich
 */

public abstract class UserDAO{

    /**
     * Inserts user into database
     * @param user User
     * @return true if insertion was successful or false if not
     */
    public abstract boolean addNew(User user);

    /**
     * Finds user by login
     * @param login String
     * @return user if exists
     */
    public abstract User findEntity(String login);

    /**
     * Finds all users
     * @return list of users
     */
    public abstract List<User> findAll();

    /**
     * Tries to update user in database
     * @param user User
     * @return updated user
     */
    public abstract User update(User user);

    /**
     * Removes user from db by e-mail
     * @param email String
     * @return true if removing was successful or false if not
     */
    public abstract boolean delete(String email);

    /**
     * Adds cash to existing user
     * @param email String
     * @param cashAmount int
     * @return updated user
     */
    public abstract User addCash(String email, int cashAmount);

    protected Connection getConnection() {
        try {
            return DataSource.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong! Connection not available.");
        }
    }
}