package com.excilys.project.computerDatabase.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.excilys.project.computerDatabase.domain.Company;
import com.excilys.project.computerDatabase.domain.Computer;
public class ComputerDAO {
	
	private static final String table = "computer";
	
	public static ComputerDAO _instance = null;
	
	public ArrayList<Computer> selectAllComputer(){
		Connection con = ConnectionManager.getConnection();
		
		ArrayList<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT * FROM "+table;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				int id = results.getInt("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				int company_id = results.getInt("company_id");
				alc.add(new Computer(id,name,introduced,discontinued,company_id));
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
	
	public ArrayList<Computer> selectAllComputerWithCompanyName(){
		Connection con = ConnectionManager.getConnection();
		
		ArrayList<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT cu.*, ca.name AS name2 FROM company AS ca "
				+ "RIGHT OUTER JOIN computer AS cu ON cu.company_id = ca.id ";
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				int id = results.getInt("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				int company_id = results.getInt("company_id");
				String companyName = results.getString("name2");
				alc.add(new Computer(id,name,introduced,discontinued,company_id, companyName));
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
	
	public ArrayList<Computer> selectAllComputerWithCompanyNameLike(String like){
		Connection con = ConnectionManager.getConnection();
		
		ArrayList<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT cu.*, ca.name AS name2 FROM company AS ca "
				+ "RIGHT OUTER JOIN computer AS cu ON cu.company_id = ca.id "
				+ "WHERE cu.name LIKE '%"+like+"%' OR ca.name LIKE '%"+like+"%'";
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				int id = results.getInt("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				int company_id = results.getInt("company_id");
				String companyName = results.getString("name2");
				alc.add(new Computer(id,name,introduced,discontinued,company_id, companyName));
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
	
	public void insertComputer(Computer c){
		Connection con = ConnectionManager.getConnection();
		
		String query = "INSERT INTO "+table+" VALUES(?,?,?,?,?)";
		
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setDate(3, c.getIntroduced());
			ps.setDate(4, c.getDiscontinued());
			ps.setInt(5, c.getCompany_id());
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
	
	public Company selectCompanyByComputerId(int idComputer){
		Connection con = ConnectionManager.getConnection();
		
		Company company = null;
		
		String query = "SELECT ca.* FROM company AS ca INNER JOIN "+table+" AS cu ON cu.company_id = ca.id WHERE cu.id = "+idComputer;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			if(results.next()){
				int id = results.getInt("id");
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
	
	synchronized public static ComputerDAO getInstance(){
		if(_instance == null){
			_instance = new ComputerDAO();
		}
		return _instance;
	}
}
