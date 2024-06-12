package com.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.example.dao.ProducttDAO;
import com.example.model.Productt;

@WebServlet("/home")
public class HomeFetchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProducttDAO producttDAO;

    public void init() {
        producttDAO = new ProducttDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Productt> productList = producttDAO.selectAllProducts();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optionally handle POST request, or you can just call doGet if the logic is the same
        doGet(request, response);
    }
}