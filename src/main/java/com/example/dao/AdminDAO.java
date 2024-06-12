package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.AdminBean;

public class AdminDAO {
    private static final String INSERT_ADMIN_SQL = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM users";
    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM admin_cred WHERE username = ?";
    private static final String DELETE_ADMIN_BY_ID = "DELETE FROM admin_cred WHERE username = ?";
    private static final String UPDATE_ADMIN_SQL = "UPDATE admin_cred SET email = ?, password = ? WHERE username = ?";

    public AdminDAO() {
    }

    public int insertAdmin(AdminBean admin) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, admin.getRole());
            return preparedStatement.executeUpdate();
        }
    }

    public List<AdminBean> getAllAdmins() {
        List<AdminBean> admins = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMINS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                admins.add(new AdminBean(username, email, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admins;
    }

    public AdminBean getAdminById(String username) {
        AdminBean admin = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {

            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    admin = new AdminBean(username, email, password);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admin;
    }

    public boolean deleteAdminById(String username) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_BY_ID)) {

            statement.setString(1, username);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateAdmin(AdminBean admin) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_SQL)) {

            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getUsername());
            return statement.executeUpdate() > 0;
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
