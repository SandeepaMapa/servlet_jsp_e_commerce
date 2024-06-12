// Response Mapper
package com.example.mapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyResponseMapper {
    public static void redirectToLoginPage(HttpServletResponse response, String message) throws IOException {
        response.sendRedirect("login.jsp?message=" + message);
    }

    public static void redirectToLoginPageWithError(HttpServletResponse response, String error) throws IOException {
        response.sendRedirect("login.jsp?error=" + error);
    }
}
