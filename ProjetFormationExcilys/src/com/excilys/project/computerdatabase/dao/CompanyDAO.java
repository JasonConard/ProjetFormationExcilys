package com.excilys.project.computerDatabase.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.excilys.project.computerDatabase.domain.Company;

public class CompanyDAO {
	
	private static final String table = "company";
	
	public static CompanyDAO _instance = null;
	
	public ArrayList<Company> selectAllCompany(){
		Connection con = ConnectionManager.getConnection();
		
		ArrayList<Company> alc = new ArrayList<Company>();
		
		String query = "SELECT * FROM "+table;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				int id = results.getInt("id");
				String name = results.getString("name");
				alc.add(new Company(id,name));
			}
			results.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("SQL query problem : "+query);
		} finally{
			try {
				con.close();
			} catch (SQLException e) {}
		}
		
		return alc;
	}
	
	public void insertCompany(Company c){
		Connection con = ConnectionManager.getConnection();
		
		String query = "INSERT INTO "+table+" VALUES(?,?)";
		
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQL query problem : "+query);
		} finally{
			try {
				con.close();
			} catch (SQLException e) {}
		}
	}
	
	synchronized public static CompanyDAO getInstance(){
		if(_instance == null){
			_instance = new CompanyDAO();
		}
		return _instance;
	}
	
}
