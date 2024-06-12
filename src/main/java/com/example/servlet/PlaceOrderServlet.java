package com.example.servlet;

import com.example.mapper.OrderRequestMapper;
import com.example.mapper.OrderResponseMapper;
import com.example.model.OrderBean;
import com.example.services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order/*")
@MultipartConfig
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize OrderService
        orderService = new OrderService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/placeOrder":
                    insertOrder(request, response);
                    break;
                case "/deleteOrder":
                    deleteOrder(request, response);
                    break;
                case "/listOrders":
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            List<OrderBean> orderList = orderService.getAllOrders();
            OrderResponseMapper.forwardToOrderList(request, response, orderList);
        } catch (SQLException e) {
            e.printStackTrace();
            OrderResponseMapper.redirectToErrorPage(request, response);
        }
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            OrderBean newOrder = OrderRequestMapper.mapToOrder(request);
            if (orderService.addOrder(newOrder)) {
                OrderResponseMapper.redirectToOrderConfirmation(request, response);
            } else {
                OrderResponseMapper.redirectToErrorPage(request, response);
            }
        } catch (IllegalArgumentException | SQLException ex) {
            ex.printStackTrace();
            OrderResponseMapper.redirectToErrorPage(request, response);
        }
    }


    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        try {
            orderService.deleteOrder(id);
            response.sendRedirect("listOrders");
        } catch (SQLException e) {
            e.printStackTrace();
            OrderResponseMapper.redirectToErrorPage(request, response);
        }
    }
}
