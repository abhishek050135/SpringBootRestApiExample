package com.websystique.springboot.model;
	
public class User {

	private int id;
	private String name;
	private int age;
	private int salary;
	private String street;
	private String city;
	private String country;
	
	public User(int id, String name, int age, int salary, String street, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.street = street;
		this.city = city;
		this.country = country;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}