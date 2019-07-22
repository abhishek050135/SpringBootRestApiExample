package com.websystique.springboot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity( name = "CountryDetails" )
@Table( name = "CountryDetails" )
public class CountryDetails  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "CountryCode")
	private Integer countryCode;
	
	@Column(name = "CountryName")
	private String countryName;
	
	public CountryDetails(Integer countryCode, String countryName) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
	public CountryDetails() {
		
	}

}
