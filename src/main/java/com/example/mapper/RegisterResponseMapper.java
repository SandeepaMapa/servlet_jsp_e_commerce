package com.example.mapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterResponseMapper {
    public static void redirectToLoginPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp?message=Please check your email to verify your account.");
    }
}
