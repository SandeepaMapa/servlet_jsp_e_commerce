package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns={"/order","/PlaceOrderForm.jsp"})
public class CustomerAuthenticationFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login.jsp";

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            // Check if user is logged in and has customer role
            String role = (String) session.getAttribute("role");
            if ("customer".equals(role)) {
                chain.doFilter(request, response); // Allow access for customer
            } else {
                response.sendRedirect(loginURI); // Redirect if not customer
            }
        } else {
            response.sendRedirect(loginURI); // Redirect if not logged in
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    public void destroy() {
        // Cleanup code
    }
}
