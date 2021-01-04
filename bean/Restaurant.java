package com.sprint.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.sun.istack.NotNull;
//Restaurant Entity
@Entity
@Table(name="restaurant",schema ="public")
public class Restaurant implements Serializable{
	@Id
	@NotNull
	@Column(name="rid")
	private int rid;
	
	@NotBlank
	@Email
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+",message = "enter email in valid format")
	@Column(name="restaurantEmail")
	private String restaurantEmail;

	
	@NotNull
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	@Column(name="restaurantPwd")
	private String restaurantPwd;
	
	@NotNull
	@Size(min=2,message="Name should atleast have 2 characters")
	@Column(name="restaurantName")
	private String restaurantName;
	
	@NotNull
	@Size(min=2,message="Address should atleast have 2 characters")
	@Column(name="restaurantAddress")
	private String restaurantAddress;
	

//Getters and Setters

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public String getRestaurantPwd() {
		return restaurantPwd;
	}

	public void setRestaurantPwd(String restaurantPwd) {
		this.restaurantPwd = restaurantPwd;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

//Default Constructor
	public Restaurant() {
		super();
	}

	

//Parameterized Constructor
	public Restaurant(int rid,
			@NotBlank @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String restaurantEmail,
			@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places") String restaurantPwd,
			@Size(min = 2, message = "Name should atleast have 2 characters") String restaurantName,
			@Size(min = 2, message = "Address should atleast have 2 characters") String restaurantAddress) {
		super();
		this.rid = rid;
		this.restaurantEmail = restaurantEmail;
		this.restaurantPwd = restaurantPwd;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
	}
//toString method
	@Override
	public String toString() {
		return "Restaurant [r_id=" + rid + ", restaurantEmail=" + restaurantEmail + ", restaurantPwd=" + restaurantPwd
				+ ", restaurantName=" + restaurantName + ", restaurantAddress=" + restaurantAddress + "]";
	}


	
	
}
