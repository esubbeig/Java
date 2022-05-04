package com.javaproj.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaproj.usermanagement.model.Products;

public class ProductsDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/ums";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_Product_SQL = "insert into products "
			+ " (name, description, color, price) values " + " (?,?,?,?);";
	private static final String SELECT_Product_BY_ID = "select id, name, description, color, price from products where id=?;";
	private static final String SELECT_ALL_Products = "select * from products;";
	private static final String UPDATE_Product_SQL = "update products set name = ?, description = ?, color = ?, price = ? where id=?;";
	private static final String DELETE_Product_SQL = "delete from products where id=?;";

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

//	To insert the product in database
	public String insertProduct(Products product) throws SQLException {

		String result = "Product added successfully!";

		loadDriver(dbDriver);
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_Product_SQL);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getColor());
			ps.setString(4, product.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "product not added!";
			e.printStackTrace();
		}
		return result;
	}

//	To update the product
	public boolean updateProduct(Products product) throws SQLException {

		loadDriver(dbDriver);
		Connection conn = getConnection();

		boolean rowUpdated = false;

		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_Product_SQL);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getColor());
			ps.setString(4, product.getPrice());
			ps.setInt(5, product.getId());
			rowUpdated = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowUpdated;
	}

//	Select product by id
	public Products selectProduct(int id) throws SQLException {

		Products product = null;

		loadDriver(dbDriver);
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_Product_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				String color = rs.getString("color");
				String price = rs.getString("price");
				product = new Products(id, name, description, color, price);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	// Fetch all the products
	public List<Products> selectAllProducts() {

		List<Products> products = new ArrayList<>();

		loadDriver(dbDriver);
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_Products);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String color = rs.getString("color");
				String price = rs.getString("price");
				products.add(new Products(id, name, description, color, price));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	// delete the user from the database
	public boolean deleteProduct(int id) throws SQLException {

		boolean rowDeleted = false;

		loadDriver(dbDriver);
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_Product_SQL);
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowDeleted;

	}

}
