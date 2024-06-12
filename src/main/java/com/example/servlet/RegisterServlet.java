// Servlet
package com.example.servlet;

import com.example.mapper.RegisterRequestMapper;
import com.example.mapper.RegisterResponseMapper;
import com.example.model.CustomerBean;
import com.example.services.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisterService registerService;

    public void init() {
        registerService = new RegisterService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerBean customer = RegisterRequestMapper.mapToCustomer(request);
        try {
            registerService.registerCustomer(customer);
            RegisterResponseMapper.redirectToLoginPage(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
