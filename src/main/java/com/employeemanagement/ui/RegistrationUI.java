package com.employeemanagement.ui;

import com.employeemanagement.controllers.RegistrationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationUI extends JFrame {
    private RegistrationController registrationController;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton cancelButton;

    public RegistrationUI(RegistrationController registrationController) {
        this.registrationController = registrationController;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Employee Management Registration");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                registrationController.onRegister(username, password);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the registration window
                dispose();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(cancelButton);

        add(panel);
    }

    public void showRegistrationSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showRegistrationErrorMessage() {
        JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // You can add more methods for UI interactions, such as navigation to other screens
}
