package com.employeemanagement.ui;

import com.employeemanagement.controllers.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private LoginController loginController;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginUI(LoginController loginController) {
        this.loginController = loginController;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Employee Management Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                loginController.onLogin(username, password);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration page
                // You can implement this part according to your UI framework
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
    }

    public void showLoginSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // You can add more methods for UI interactions, such as navigation to other screens
}
