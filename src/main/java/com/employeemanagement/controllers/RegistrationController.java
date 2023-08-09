package com.employeemanagement.controllers;

import com.employeemanagement.services.RegistrationService;
import com.employeemanagement.ui.RegistrationUI;

public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void startRegistration() {
        // Instantiate the RegistrationUI and show it to the user
        RegistrationUI registrationUI = new RegistrationUI(this);
        registrationUI.setVisible(true);
    }

    public void onRegister(String username, String password) {
        boolean success = registrationService.registerUser(username, password);

        if (success) {
            // Display success message and navigate to login UI
        } else {
            // Display registration failure message in the UI
        }
    }
}
