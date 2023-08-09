package com.employeemanagement.data;

import com.employeemanagement.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsername(String username) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?")) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(String username) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE username = ?")) {
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
