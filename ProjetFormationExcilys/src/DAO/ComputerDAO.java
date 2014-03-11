package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import Entity.Computer;

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
		} catch (SQLException e) {
			System.out.println("SQL query problem : "+query);
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
		}catch(Exception e){}
		
	}
	
	synchronized public static ComputerDAO getInstance(){
		if(_instance == null){
			_instance = new ComputerDAO();
		}
		return _instance;
	}
}
