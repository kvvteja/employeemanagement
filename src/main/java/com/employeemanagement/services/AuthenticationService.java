package com.employeemanagement.services;

import com.employeemanagement.data.UserDao;
import com.employeemanagement.models.User;

public class AuthenticationService {
    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authenticate(String username, String password) {
        User user = userDao.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
