package com.example.mapper;

import jakarta.servlet.http.HttpServletRequest;
import com.example.model.AdminBean;
import java.io.IOException;

public class AdminRequestMapper {

    public static AdminBean mapToAdmin(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "admin"; // Default role

        return new AdminBean(username, email, password, role);
    }
}
