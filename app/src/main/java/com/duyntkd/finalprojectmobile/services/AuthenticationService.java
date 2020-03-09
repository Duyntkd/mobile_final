package com.duyntkd.finalprojectmobile.services;

import com.duyntkd.finalprojectmobile.LoginActivity;

public class AuthenticationService {

    public Object requestLogin(String userName, String password) {
        userName = userName.trim();
        password = password.trim();
        if(userName.isEmpty() || password.isEmpty()) return null;
        if (userName.equals(LoginActivity.ROLE_ADMIN)) return LoginActivity.ROLE_ADMIN;
        if (userName.equals(LoginActivity.ROLE_MANAGER)) return LoginActivity.ROLE_MANAGER;
        return LoginActivity.ROLE_EMPLOYEE;
    }
}
