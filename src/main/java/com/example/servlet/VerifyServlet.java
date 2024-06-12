// Servlet
package com.example.servlet;

import com.example.mapper.VerifyRequestMapper;
import com.example.mapper.VerifyResponseMapper;
import com.example.services.VerifyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    private VerifyService verifyService;

    public void init() {
        verifyService = new VerifyService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = VerifyRequestMapper.extractToken(request);
        try {
            verifyService.verifyCustomer(token);
            VerifyResponseMapper.redirectToLoginPage(response, "Account verified, please login.");
            System.out.println("Success Case");
        } catch (SQLException e) {
            e.printStackTrace();
            VerifyResponseMapper.redirectToLoginPageWithError(response, "Internal server error occurred.");
            System.out.println("Error Case");
        }
    }
}
