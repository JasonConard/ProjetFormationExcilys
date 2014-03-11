package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static Connection conn;
	private static final String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String usr = "jee-cdb";
	private static final String pwd = "password";
	
	public static Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,usr,pwd);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			System.out.println("Connection problem with JDBC ... ");
			e.printStackTrace();
		}
		return conn;
	}
}
