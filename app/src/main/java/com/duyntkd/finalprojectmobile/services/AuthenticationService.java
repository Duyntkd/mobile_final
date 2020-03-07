package com.duyntkd.finalprojectmobile.services;

public class AuthenticationService {

    public Object requestLogin(String userName, String password) {
        userName = userName.trim();
        password = password.trim();
        if(userName.isEmpty() || password.isEmpty()) return null;
        return "Employee";
    }
}
