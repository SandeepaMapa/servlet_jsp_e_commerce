package com.example.dao;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Productt;

public class ProducttDAO {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO productt (productId, title, description, productImage1, productImage2, productImage3, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT productId, title, description, productImage1, productImage2, productImage3, price FROM productt WHERE productId = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT productId, title, description, productImage1, productImage2, productImage3, price FROM productt";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM productt WHERE productId = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE productt SET title = ?, description = ?, price = ? WHERE productId = ?";

    public ProducttDAO() {
    }

    protected Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public int insertProduct(Productt product) throws SQLException {
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBinaryStream(4, new ByteArrayInputStream(product.getProductImage1()));
            preparedStatement.setBinaryStream(5, new ByteArrayInputStream(product.getProductImage2()));
            preparedStatement.setBinaryStream(6, new ByteArrayInputStream(product.getProductImage3()));
            preparedStatement.setString(7, product.getPrice());
            result = preparedStatement.executeUpdate();
        }
        return result;
    }

    public Productt selectProduct(String productId) {
        Productt product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setString(1, productId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    byte[] productImage1 = rs.getBytes("productImage1");
                    byte[] productImage2 = rs.getBytes("productImage2");
                    byte[] productImage3 = rs.getBytes("productImage3");
                    String price = rs.getString("price");
                    product = new Productt(productId, title, description, productImage1, productImage2, productImage3, price);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List<Productt> selectAllProducts() {
        List<Productt> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String productId = rs.getString("productId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                byte[] productImage1 = rs.getBytes("productImage1");
                byte[] productImage2 = rs.getBytes("productImage2");
                byte[] productImage3 = rs.getBytes("productImage3");
                String price = rs.getString("price");
                products.add(new Productt(productId, title, description, productImage1, productImage2, productImage3, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public boolean deleteProduct(String productId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setString(1, productId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduct(Productt product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getPrice());
            statement.setString(4, product.getProductId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            rowUpdated = false;
        }
        return rowUpdated;
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
