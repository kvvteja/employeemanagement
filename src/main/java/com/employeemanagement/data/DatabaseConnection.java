package com.employeemanagement.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:path_to_your_database.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC driver", e);
        }
    }
}
