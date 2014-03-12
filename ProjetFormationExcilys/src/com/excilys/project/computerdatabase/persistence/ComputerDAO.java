package com.excilys.project.computerdatabase.persistence;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import com.excilys.project.computerdatabase.domain.Company;
import com.excilys.project.computerdatabase.domain.Computer;

public class ComputerDAO {
	
	private static final String table = "computer";
	
	public static ComputerDAO instance = null;
	
	public List<Computer> selectAllComputer(){
		Connection con = ConnectionManager.getConnection();
		
		List<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT * FROM "+table;
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				long id = results.getLong("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				long companyId = results.getLong("company_id");
				alc.add(new Computer(id,name,introduced,discontinued,companyId));
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
	
	private void closeAll(ResultSet rs,PreparedStatement ps, Connection c){
		try {
			rs.close();
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Computer> selectAllComputerWithCompanyName(){
		Connection con = ConnectionManager.getConnection();
		
		List<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT cu.*, ca.name AS name2 FROM company AS ca "
				+ "RIGHT OUTER JOIN computer AS cu ON cu.company_id = ca.id ";
		ResultSet results = null;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = con.prepareStatement(query);
			results = preparedStatement.executeQuery();
			while(results.next()){
				long id = results.getLong("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				long companyId = results.getLong("company_id");
				String companyName = results.getString("name2");
				alc.add(new Computer(id,name,introduced,discontinued,companyId,companyName));
			}
			results.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println("SQL query problem : "+query);
		} finally{
			closeAll(results, preparedStatement, con);
		}
		
		return alc;
	}
	
	public List<Computer> selectAllComputerWithCompanyNameLike(String like){
		Connection con = ConnectionManager.getConnection();
		
		List<Computer> alc = new ArrayList<Computer>();
		
		String query = "SELECT cu.*, ca.name AS name2 FROM company AS ca "
				+ "RIGHT OUTER JOIN computer AS cu ON cu.company_id = ca.id "
				+ "WHERE cu.name LIKE '%"+like+"%' OR ca.name LIKE '%"+like+"%'";
		ResultSet results;
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			while(results.next()){
				long id = results.getLong("id");
				String name = results.getString("name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				long companyId = results.getLong("company_id");
				String companyName = results.getString("name2");
				alc.add(new Computer(id,name,introduced,discontinued,companyId,companyName));
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
			ps.setLong(1, c.getId());
			
			ps.setString(2, c.getName());
			
			if(c.getIntroduced()!=null){
				ps.setDate(3,  new java.sql.Date(c.getIntroduced().getTime()));
			}else{
				ps.setNull(3, Types.TIMESTAMP);
			}
			
			if(c.getDiscontinued()!=null){
				ps.setDate(4, new java.sql.Date(c.getDiscontinued().getTime()));
			}else{
				ps.setNull(4, Types.TIMESTAMP);
			}
			
			if(c.getCompany() != null){
				ps.setLong(5, c.getCompany().getId());
			}else{
				ps.setNull(5, Types.BIGINT);
			}
			
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
	
	public Company selectCompanyByComputerId(long idComputer){
		Connection con = ConnectionManager.getConnection();
		
		Company company = null;
		
		String query = "SELECT ca.* FROM company AS ca INNER JOIN "+table+" AS cu ON cu.company_id = ca.id WHERE cu.id = "+idComputer;
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
	

	public void delete(long id) {
		Connection con = ConnectionManager.getConnection();
		
		String query = "DELETE FROM "+table+" WHERE id = ?";
		
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, id);
				
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
	
	synchronized public static ComputerDAO getInstance(){
		if(instance == null){
			instance = new ComputerDAO();
		}
		return instance;
	}
}
