package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.Company;

public class CompanyDAO {
	
	private static final String url = "jdbc:mysql://127.0.0.1:3306/projetFormationExcilys";
	private static final String usr = "root";
	private static final String pwd = "msqlmdp";
	
	private static final String table = "company";
	
	public ArrayList<Company> selectAllCompany(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url,usr,pwd);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			System.out.println("Connection problem with JDBC ... ");
			e.printStackTrace();
		}
		
		String query = "SELECT * FROM "+table;
		ResultSet results;
		Statement stmt;
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				System.out.println("Nom : "+ results.getString("name"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL query problem : "+query);
		}
		
		return null;
	}
	
	public static void main(String args[]){
		System.out.println("BEGIN");
		//CompanyDAO cdao = new CompanyDAO();
		//cdao.selectAllCompany();
	}
}
