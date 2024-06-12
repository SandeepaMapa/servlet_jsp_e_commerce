// Request Mapper
package com.example.mapper;

import jakarta.servlet.http.HttpServletRequest;

public class VerifyRequestMapper {
    public static String extractToken(HttpServletRequest request) {
        return request.getParameter("token");
    }
}
