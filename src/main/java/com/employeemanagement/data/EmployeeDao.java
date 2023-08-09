package com.employeemanagement.data;

import com.employeemanagement.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employeeId"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                employee.setDateOfJoining(resultSet.getDate("dateOfJoining"));
                employee.setGrade(resultSet.getString("grade"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public boolean addEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employees (firstName, lastName, dateOfBirth, dateOfJoining, grade) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
            statement.setDate(4, new java.sql.Date(employee.getDateOfJoining().getTime()));
            statement.setString(5, employee.getGrade());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employees SET firstName = ?, lastName = ?, dateOfBirth = ?, dateOfJoining = ?, grade = ? WHERE employeeId = ?")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
            statement.setDate(4, new java.sql.Date(employee.getDateOfJoining().getTime()));
            statement.setString(5, employee.getGrade());
            statement.setInt(6, employee.getEmployeeId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteEmployee(int employeeId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE employeeId = ?")) {
            statement.setInt(1, employeeId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Employee getEmployeeById(int employeeId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE employeeId = ?")) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employeeId"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                employee.setDateOfJoining(resultSet.getDate("dateOfJoining"));
                employee.setGrade(resultSet.getString("grade"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
