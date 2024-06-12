package com.example.mapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.example.model.OrderBean;

public class OrderResponseMapper {

    public static void forwardToOrderList(HttpServletRequest request, HttpServletResponse response, List<OrderBean> orderList)
            throws ServletException, IOException {
        request.setAttribute("orderList", orderList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-list.jsp");
        dispatcher.forward(request, response);
    }

    public static void redirectToOrderConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/home");
    }

    public static void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}
