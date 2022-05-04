package com.javaproj.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaproj.usermanagement.model.User;

public class UsersDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/ums";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_USERS_SQL = "insert into user " + " (username, email, password) values "
			+ " (?,?,?);";
	
	private static final String FETCH_USER_SQL = "select id from user where email=? and password=?;";

	// step 1: load the driver
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// step 2: establish the connection
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// insert users into the database
	public boolean saveUser(User user) throws SQLException {

		loadDriver(dbDriver);
		Connection conn = getConnection();
		
		boolean set = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_USERS_SQL);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			set = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;

	}
	
//	fetch user details
//	Select user by id
	public boolean fetchUser(User user) throws SQLException {

		boolean found = false;

		loadDriver(dbDriver);
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(FETCH_USER_SQL);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();
			
			found = rs.next();
			
			System.out.println(found);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return found;
	}

}
