package com.employeemanagement.controllers;

import com.employeemanagement.models.Employee;
import com.employeemanagement.services.EmployeeService;
import com.employeemanagement.ui.EmployeeSearchUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeSearchController {
    private EmployeeService employeeService;

    public EmployeeSearchController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void startEmployeeSearch() {
        // Instantiate the EmployeeSearchUI and show it to the user
        EmployeeSearchUI employeeSearchUI = new EmployeeSearchUI(this);
        employeeSearchUI.setVisible(true);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public void deleteEmployee(int employeeId) {
        EmployeeSearchUI employeeSearchUI = new EmployeeSearchUI(this);
        boolean success = employeeService.deleteEmployee(employeeId);
        employeeSearchUI.refreshTable();
    }

    public void exportToCSV(List<Employee> employees, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Employee ID,First Name,Last Name,Date of Birth,Date of Joining,Grade\n");

            for (Employee employee : employees) {
                writer.write(
                        employee.getEmployeeId() + "," +
                                employee.getFirstName() + "," +
                                employee.getLastName() + "," +
                                employee.getDateOfBirth() + "," +
                                employee.getDateOfJoining() + "," +
                                employee.getGrade() + "\n"
                );
            }

            // Display success message
        } catch (IOException e) {
            e.printStackTrace();
            // Display error message
        }
    }

    // Add additional methods for editing, viewing, or any other employee-related actions
}
