package com.example.dao;

import com.example.model.CustomerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String INSERT_PENDING_USER_SQL = "INSERT INTO pending_users (email, password, role, token) VALUES (?, ?, ?, ?)";
    private static final String SELECT_PENDING_USER_BY_TOKEN_SQL = "SELECT * FROM pending_users WHERE token = ?";
    private static final String DELETE_PENDING_USER_SQL = "DELETE FROM pending_users WHERE token = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?)";

    public void registerPendingCustomer(CustomerBean customer, String token) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PENDING_USER_SQL)) {
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getRole());
            preparedStatement.setString(4, token);
            preparedStatement.executeUpdate();
        }
    }

    public CustomerBean getPendingCustomerByToken(String token) throws SQLException {
        CustomerBean customer = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_USER_BY_TOKEN_SQL)) {
            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                customer = new CustomerBean(rs.getString("email"), rs.getString("password"), rs.getString("role"));
            }
        }
        return customer;
    }

    public void deletePendingCustomer(String token) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PENDING_USER_SQL)) {
            preparedStatement.setString(1, token);
            preparedStatement.executeUpdate();
        }
    }

    public void registerCustomer(CustomerBean customer) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getEmail()); // Assuming username is same as email
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setString(4, customer.getRole());
            preparedStatement.executeUpdate();
        }
    }
}