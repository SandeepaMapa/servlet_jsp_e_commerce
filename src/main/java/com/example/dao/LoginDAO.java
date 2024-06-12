package com.example.dao;

import com.example.model.LoginBean;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	public String validate(LoginBean loginBean) {
		String role = null;

		String query = "SELECT password, role FROM users WHERE username = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, loginBean.getUsername());
			try (ResultSet rs = preparedStatement.executeQuery()) {

				if (rs.next()) {
					String storedPasswordHash = rs.getString("password");
					String dbRole = rs.getString("role");

					// Verify password and get role
					if (BCrypt.checkpw(loginBean.getPassword(), storedPasswordHash)) {
						role = dbRole;
					}
				}
			}
		} catch (SQLException e) {
			// Process SQL exception
			printSQLException(e);
		}
		return role;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
