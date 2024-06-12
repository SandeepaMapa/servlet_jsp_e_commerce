package com.example.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.services.LoginService;
import com.example.model.LoginBean;
import com.example.mapper.LoginRequestMapper;
import com.example.mapper.LoginResponseMapper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	public void init() {
		loginService = new LoginService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginBean loginBean = LoginRequestMapper.mapToLoginBean(request);
		String role = loginService.validate(loginBean);

		HttpSession session = request.getSession();
		if (role != null) {
			session.setAttribute("username", loginBean.getUsername());
			session.setAttribute("role", role); // Set role attribute in session
			request.setAttribute("welcomeMessage", "Welcome " + loginBean.getUsername());
			if ("admin".equals(role)) {
				LoginResponseMapper.forwardToAdminDashboard(request, response);
			} else if ("customer".equals(role)) {
				LoginResponseMapper.forwardToHome(request, response);
			} else {
				LoginResponseMapper.forwardToLoginPage(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials. Please try again.");
			LoginResponseMapper.forwardToLoginPage(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle GET requests here
		// For example, you can forward the user to the login page
		LoginResponseMapper.forwardToLoginPage(request, response);
	}
}
