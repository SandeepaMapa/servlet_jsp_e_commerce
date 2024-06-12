package com.example.dao;

import com.example.model.OrderBean;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String INSERT_ORDER_SQL = "INSERT INTO orders (orderId, email, name, address, item, quantity, payment_slip, total, timestamp, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?)";
    private static final String SELECT_ORDER_BY_ID = "SELECT orderId, email, name, address, item, quantity, payment_slip, total, timestamp, status FROM orders WHERE orderId = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT orderId, email, name, address, item, quantity, payment_slip, total, timestamp, status FROM orders";
    private static final String DELETE_ORDER_SQL = "DELETE FROM orders WHERE orderId = ?";
    private static final String UPDATE_ORDER_SQL = "UPDATE orders SET email = ?, name = ?, address = ?, item = ?, quantity = ?, payment_slip = ?, total = ?, status = ? WHERE orderId = ?";

    public OrderDAO() {
    }

    protected Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public int insertOrder(OrderBean order) throws SQLException {
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
            preparedStatement.setString(1, order.getOrderId());
            preparedStatement.setString(2, order.getEmail());
            preparedStatement.setString(3, order.getName());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setString(5, order.getItem());
            preparedStatement.setInt(6, order.getQuantity());
            preparedStatement.setBinaryStream(7, new ByteArrayInputStream(order.getPaymentSlip().getBytes(1, (int) order.getPaymentSlip().length())));
            preparedStatement.setString(8, order.getTotal());
            preparedStatement.setString(9, order.getStatus());
            result = preparedStatement.executeUpdate();
        }
        return result;
    }

    public OrderBean selectOrder(String orderId) {
        OrderBean order = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setString(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String item = rs.getString("item");
                int quantity = rs.getInt("quantity");
                Blob paymentSlip = rs.getBlob("payment_slip");
                String total = rs.getString("total");
                String status = rs.getString("status");
                String timestamp = rs.getString("timestamp");
                order = new OrderBean(orderId, email, name, address, item, quantity, paymentSlip, total, status, timestamp);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return order;
    }

    public List<OrderBean> selectAllOrders() {
        List<OrderBean> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString("orderId");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String item = rs.getString("item");
                int quantity = rs.getInt("quantity");
                Blob paymentSlip = rs.getBlob("payment_slip");
                String total = rs.getString("total");
                String status = rs.getString("status");
                String timestamp = rs.getString("timestamp");
                orders.add(new OrderBean(orderId, email, name, address, item, quantity, paymentSlip, total, status, timestamp));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }

    public boolean deleteOrder(String orderId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_SQL)) {
            statement.setString(1, orderId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateOrder(OrderBean order) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_SQL)) {
            statement.setString(1, order.getEmail());
            statement.setString(2, order.getName());
            statement.setString(3, order.getAddress());
            statement.setString(4, order.getItem());
            statement.setInt(5, order.getQuantity());
            statement.setBinaryStream(6, new ByteArrayInputStream(order.getPaymentSlip().getBytes(1, (int) order.getPaymentSlip().length())));
            statement.setString(7, order.getTotal());
            statement.setString(8, order.getStatus());
            statement.setString(9, order.getOrderId());
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
