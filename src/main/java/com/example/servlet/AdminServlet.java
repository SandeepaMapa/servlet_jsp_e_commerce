package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.services.AdminService;
import com.example.model.AdminBean;
import com.example.mapper.AdminRequestMapper;
import com.example.mapper.AdminResponseMapper;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminService adminService;

    public void init() {
        adminService = new AdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("-------------" + action);

        try {
            switch (action) {
                case "/new":
                    insertAdmin(request, response);
                    break;
                case "/delete":
                    deleteAdmin(request, response);
                    break;
                case "/update":
                    updateAdmin(request, response);
                    break;
                case "/list":
                    listAdmin(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<AdminBean> listAdmin = adminService.getAllAdmins();
        AdminResponseMapper.forwardToAdminList(request, response, listAdmin);
    }

    private void insertAdmin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
        try {
            AdminBean newAdmin = AdminRequestMapper.mapToAdmin(request);
            adminService.addAdmin(newAdmin);
            String successMessage = "The new admin " + newAdmin.getUsername() + " added successfully!";
            AdminResponseMapper.forwardToAddAdminPage(request, response, successMessage);

        } catch (IllegalArgumentException ex) {
            AdminResponseMapper.forwardToErrorPage(request, response, ex.getMessage());
        }
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            AdminBean admin = AdminRequestMapper.mapToAdmin(request);
            adminService.updateAdmin(admin);
            response.sendRedirect("list");
        } catch (IllegalArgumentException ex) {
            AdminResponseMapper.forwardToErrorPage(request, response, ex.getMessage());
        }
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        adminService.deleteAdmin(username);
        response.sendRedirect("list");
    }
}
