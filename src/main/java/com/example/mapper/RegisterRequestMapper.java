package com.example.mapper;

import com.example.model.CustomerBean;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterRequestMapper {
    public static CustomerBean mapToCustomer(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // You can add more mapping logic here if needed
        return new CustomerBean(email, password, "customer");
    }
}
