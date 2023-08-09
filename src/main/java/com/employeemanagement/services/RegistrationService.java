package com.employeemanagement.services;

import com.employeemanagement.data.UserDao;
import com.employeemanagement.models.User;

public class RegistrationService {
    private UserDao userDao;

    public RegistrationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(String username, String password) {
        User existingUser = userDao.getUserByUsername(username);

        if (existingUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);

            return userDao.addUser(newUser);
        }

        return false;
    }
}
