package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private final static String URL = "jdbc:mysql://localhost:3306/emp_management_db";

	private final static String USER = "emp_sample";
	private final static String PASSWORD = "pass";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
