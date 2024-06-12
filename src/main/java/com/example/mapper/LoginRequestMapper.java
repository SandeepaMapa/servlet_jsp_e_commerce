package com.example.mapper;

import jakarta.servlet.http.HttpServletRequest;

import com.example.model.LoginBean;

public class LoginRequestMapper {

    public static LoginBean mapToLoginBean(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        return loginBean;
    }
}
