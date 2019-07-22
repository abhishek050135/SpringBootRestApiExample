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

@Entity( name = "UserInformation" )
@Table( name = "UserInformation" )
public class UserInformation  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Age")
	private Integer age;
	
	@Column(name = "Salary")
	private Integer salary;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userAddressId", foreignKey = @ForeignKey(name = "fk_userAddress"))
	private UserAddress useraddress;

	public UserInformation() {
	}

	public UserInformation(UserAddress useraddress) {
		this.useraddress = useraddress;
	}

	public UserInformation(UserAddress useraddress, String name, Integer age, Integer salary) {
		this.useraddress = useraddress;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserAddress getUseraddress() {
		return this.useraddress;
	}

	public void setUseraddress(UserAddress useraddress) {
		this.useraddress = useraddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSalary() {
		return this.salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Userinformation [userId=" + userId + ", name=" + name + ", age=" + age + ", salary=" + salary
				+ ", useraddress=" + useraddress + "]";
	}
}
