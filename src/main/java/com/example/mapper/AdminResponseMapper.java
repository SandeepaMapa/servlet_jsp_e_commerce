package com.example.mapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.example.model.AdminBean;
import jakarta.servlet.ServletException;

public class AdminResponseMapper {

    public static void forwardToAdminList(HttpServletRequest request, HttpServletResponse response, List<AdminBean> adminList) throws ServletException, IOException {
        request.setAttribute("listAdmin", adminList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminList.jsp");
        dispatcher.forward(request, response);
    }

    public static void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        dispatcher.forward(request, response);
    }
    public static void forwardToAddAdminPage(HttpServletRequest request, HttpServletResponse response, String successMessage) throws ServletException, IOException {
        String redirectUrl = request.getContextPath() + "/add-admin.jsp?successMessage=" + successMessage;
        response.sendRedirect(redirectUrl);
    }

}
