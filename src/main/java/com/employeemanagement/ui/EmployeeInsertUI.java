package com.employeemanagement.ui;

import com.employeemanagement.controllers.EmployeeInsertController;
import com.employeemanagement.models.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeInsertUI extends JFrame {
    private EmployeeInsertController employeeInsertController;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField dojField;
    private JTextField gradeField;
    private JButton saveButton;
    private JButton cancelButton;

    public EmployeeInsertUI(EmployeeInsertController employeeInsertController) {
        this.employeeInsertController = employeeInsertController;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Insert Employee");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel dobLabel = new JLabel("Date of Birth:");
        JLabel dojLabel = new JLabel("Date of Joining:");
        JLabel gradeLabel = new JLabel("Grade:");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        dobField = new JTextField(20);
        dojField = new JTextField(20);
        gradeField = new JTextField(20);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String dob = dobField.getText();
                String doj = dojField.getText();
                String grade = gradeField.getText();

                Employee employee = new Employee();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                try {
                    employee.setDateOfBirth(new SimpleDateFormat("YYYY-MM-DD").parse(dob));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                try {
                    employee.setDateOfJoining(new SimpleDateFormat("YYYY-MM-DD").parse(doj));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                employee.setGrade(grade);

                boolean success = employeeInsertController.insertEmployee(employee);
                if (success) {
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(dobLabel);
        panel.add(dobField);
        panel.add(dojLabel);
        panel.add(dojField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
    }
}
