package com.employeemanagement.controllers;

import com.employeemanagement.MainApp;
import com.employeemanagement.services.AuthenticationService;
import com.employeemanagement.ui.LoginUI;

public class LoginController {
    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void startLogin() {
        // Instantiate the LoginUI and show it to the user
        LoginUI loginUI = new LoginUI(this);
        loginUI.setVisible(true);
    }

    public void onLogin(String username, String password) {
        if (authenticationService.authenticate(username, password) !=null) {
            MainApp.showEmployeeSearchPage();
        }
    }
}
