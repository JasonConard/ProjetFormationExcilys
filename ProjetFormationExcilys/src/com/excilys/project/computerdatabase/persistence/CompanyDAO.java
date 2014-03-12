package com.excilys.project.computerdatabase.persistence;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import com.excilys.project.computerdatabase.domain.Company;


public class CompanyDAO {
	
	private static final String table = "company";
	
	public static CompanyDAO instance = null;
	
	public List<Company> selectAllCompany(){
		Connection con = ConnectionManager.getConnection();
		
		List<Company> alc = new ArrayList<Company>();
		
		String query = "SELECT * FROM "+table;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				long id = results.getLong("id");
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
			ps.setLong(1, c.getId());
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
	
	public Company retrieveByCompanyId(long idCompany){
		Connection con = ConnectionManager.getConnection();
		
		Company company = null;
		
		String query = "SELECT ca.* FROM company AS ca WHERE ca.id = "+idCompany;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			if(results.next()){
				long id = results.getLong("id");
				String name = results.getString("name");
				company = new Company(id,name);
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
		
		return company;
	}
	
	synchronized public static CompanyDAO getInstance(){
		if(instance == null){
			instance = new CompanyDAO();
		}
		return instance;
	}
	
}
