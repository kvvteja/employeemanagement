package com.employeemanagement.controllers;

import com.employeemanagement.models.Employee;
import com.employeemanagement.services.EmployeeService;
import com.employeemanagement.ui.EmployeeInsertUI;

public class EmployeeInsertController {
    private EmployeeService employeeService;

    public EmployeeInsertController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void startEmployeeInsert() {
        // Instantiate the EmployeeInsertUI and show it to the user
        EmployeeInsertUI employeeInsertUI = new EmployeeInsertUI(this);
        employeeInsertUI.setVisible(true);
    }

    public boolean insertEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

}
