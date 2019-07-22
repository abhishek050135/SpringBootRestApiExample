package com.websystique.springboot.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity( name = "UserAddress" )
@Table( name = "UserAddress" )
public class UserAddress implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "id")
	private Integer id;

	@Column(name = "Street")
	private String street;
	
	@Column(name = "City")
	private String city;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CountryCode", foreignKey = @ForeignKey(name = "fk_countryCode"), nullable = true)
	private CountryDetails countrydetails;
	
	public UserAddress() {
	}

	public UserAddress(Integer id, String street, String city, CountryDetails countrydetails) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.countrydetails = countrydetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CountryDetails getCountrydetails() {
		return countrydetails;
	}

	public void setCountrydetails(CountryDetails countrydetails) {
		this.countrydetails = countrydetails;
	}

}
