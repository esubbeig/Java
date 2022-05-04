package com.javaproj.usermanagement.model;

public class Products {
	private int id;
	private String name;
	private String description;
	private String color;
	private String price;
	
	public Products(int id, String name, String description, String color, String price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
	}

	public Products(String name, String description, String color, String price) {
		super();
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
