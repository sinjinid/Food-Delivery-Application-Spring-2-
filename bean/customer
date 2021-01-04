package com.sprint.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity //Indicates class is an entity and is mapped to database table

@Table(name="customer") //Indicates table name
public class Customer {
	
	@Id // Primary key field
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank //Valid as long as it's not null and length is greater then 0
	@Email //To validate email
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+",message = "enter email in valid format")
	@Column(nullable = false) //Mapping between entity attribute and database column
	private String customerEmail;
	
	@NotNull //valid as long as it's not null but it can be empty.
	@Size(min=8,message="Password should atleast have 8 characters")
	@Pattern(regexp="(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}")
	@Column(nullable = false)
	private String customerPwd;
	
	@NotNull
	@Size(min=2,message="First_Name should atleast have 2 characters")
	@Column(nullable = false)
	private String customerFname;
	
	@NotNull
	@Size(min=2,message="Last_Name should atleast have 2 characters")
	@Column(nullable=false)
	private String customerLname;
	
	@NotNull
	@Size(min=2,message="Address should atleast have 2 characters")
	@Column(nullable=false)
	private String address;
	
	@NotNull
	@Size(min=10,message="Enter 10 digit Phone_no")
	@Column(nullable=false)
	private String phoneNo;
	
	//ManyToMany relation between Customer and orders
	@ManyToMany
	private List<Order> order;

	//getter and setter for order
	public List<Order> getOrder() {
		return order;
	}


	public void setOrder(List<Order> order) {
		this.order = order;
	}

	//Default Constructor
	public Customer() {}

	//Customer constructor using fields or constructor with parameters
	public Customer(int id, String customerEmail, String customerPwd, String customerFname, String customerLname,
			String address, String phoneNo) {
		super();
		this.id = id;
		this.customerEmail = customerEmail;
		this.customerPwd = customerPwd;
		this.customerFname = customerFname;
		this.customerLname = customerLname;
		this.address = address;
		this.phoneNo = phoneNo;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPwd() {
		return customerPwd;
	}

	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}

	public String getCustomerFname() {
		return customerFname;
	}

	public void setCustomerFname(String customerFname) {
		this.customerFname = customerFname;
	}

	public String getCustomerLname() {
		return customerLname;
	}

	public void setCustomerLname(String customerLname) {
		this.customerLname = customerLname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerEmail=" + customerEmail + ", customerPwd=" + customerPwd
				+ ", customerFname=" + customerFname + ", customerLname=" + customerLname + ", address=" + address
				+ ", phoneNo=" + phoneNo + "]";
	}
	
	
}
