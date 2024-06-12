package com.example.mapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.example.model.Productt;

public class ProductResponseMapper {

    public static void forwardToList(HttpServletRequest request, HttpServletResponse response, List<Productt> productList) throws ServletException, IOException, ServletException {
       request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productt-list.jsp");
        dispatcher.forward(request, response);


    }

    public static void forwardToForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("productt-form.jsp");
        dispatcher.forward(request, response);
    }

    public static void forwardToEditForm(HttpServletRequest request, HttpServletResponse response, Productt product) throws ServletException, IOException {
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }

    public static void forwardWithMessage(HttpServletRequest request, HttpServletResponse response, String message, String page) throws ServletException, IOException {
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.jsp");
        dispatcher.forward(request, response);
    }

    public static void redirect(HttpServletResponse response, String location) throws IOException {
        response.sendRedirect(location);
    }
}
