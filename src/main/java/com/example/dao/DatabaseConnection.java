package com.example.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String JDBC_URL = dotenv.get("JDBC_URL");
    private static final String JDBC_USERNAME = dotenv.get("JDBC_USERNAME");
    private static final String JDBC_PASSWORD = dotenv.get("JDBC_PASSWORD");

    public static Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            System.out.println("JDBC_URL: " + JDBC_URL);
            System.out.println("JDBC_USERNAME: " + JDBC_USERNAME);
            System.out.println("JDBC_PASSWORD: " + JDBC_PASSWORD);

            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            System.out.println("Connection established: " + (conn != null));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
