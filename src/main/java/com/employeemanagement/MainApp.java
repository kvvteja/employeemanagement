package com.employeemanagement;

import com.employeemanagement.controllers.*;
import com.employeemanagement.data.DatabaseConnection;
import com.employeemanagement.data.EmployeeDao;
import com.employeemanagement.data.UserDao;
import com.employeemanagement.services.*;

import java.sql.Connection;

public class MainApp {
    private static LoginController loginController;
    private static RegistrationController registrationController;
    private static EmployeeSearchController employeeSearchController;
    private static EmployeeInsertController employeeInsertController;
    private static EmployeeModifyController employeeModifyController;

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection(); // Initialize your database connection

        UserDao userDao = new UserDao((Connection) databaseConnection);
        EmployeeDao employeeDao = new EmployeeDao((Connection) databaseConnection);

        AuthenticationService authenticationService = new AuthenticationService(userDao);
        RegistrationService registrationService = new RegistrationService(userDao);
        EmployeeService employeeService = new EmployeeService(employeeDao);

        loginController = new LoginController(authenticationService);
        registrationController = new RegistrationController(registrationService);
        employeeSearchController = new EmployeeSearchController(employeeService);
        employeeInsertController = new EmployeeInsertController(employeeService);
        employeeModifyController = new EmployeeModifyController(employeeService);

        // Start the application by showing the login UI
        loginController.startLogin();
    }

    public static void showEmployeeSearchPage() {
        employeeSearchController.startEmployeeSearch();
    }

    public static void showRegistrationPage() {
        registrationController.startRegistration();
    }

    public static void showEmployeeInsertPage() {
        employeeInsertController.startEmployeeInsert();
    }

    public static void showEmployeeModifyPage(int employeeId) {
        employeeModifyController.startEmployeeModify(employeeId);
    }
}
