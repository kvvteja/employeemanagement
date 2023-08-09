package com.employeemanagement.services;

import com.employeemanagement.data.EmployeeDao;
import com.employeemanagement.models.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public boolean deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }
}
