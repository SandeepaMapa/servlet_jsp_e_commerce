package com.example.services;

import com.example.dao.OrderDAO;
import com.example.model.OrderBean;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public List<OrderBean> getAllOrders() throws SQLException {
        return orderDAO.selectAllOrders();
    }

    public boolean addOrder(OrderBean order) throws SQLException {
        return orderDAO.insertOrder(order) > 0;
    }

    public void deleteOrder(String id) throws SQLException {
        orderDAO.deleteOrder(id);
    }
}
