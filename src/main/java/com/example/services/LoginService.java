package com.example.services;

import com.example.dao.LoginDAO;
import com.example.model.LoginBean;

public class LoginService {

    private LoginDAO loginDao;

    public LoginService() {
        this.loginDao = new LoginDAO();
    }

    public String validate(LoginBean loginBean) {
        return loginDao.validate(loginBean);
    }
}
