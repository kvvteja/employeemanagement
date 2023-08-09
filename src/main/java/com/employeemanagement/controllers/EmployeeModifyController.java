package com.employeemanagement.controllers;

import com.employeemanagement.models.Employee;
import com.employeemanagement.services.EmployeeService;
import com.employeemanagement.ui.EmployeeModifyUI;

public class EmployeeModifyController {
    private EmployeeService employeeService;

    public EmployeeModifyController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void startEmployeeModify(int employeeId) {
        // Instantiate the EmployeeModifyUI and show it to the user
        EmployeeModifyUI employeeModifyUI = new EmployeeModifyUI(this, employeeId);
        employeeModifyUI.setVisible(true);
    }

    public boolean modifyEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

}
