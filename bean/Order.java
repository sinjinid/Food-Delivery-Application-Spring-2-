import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;


@Entity
@Table(name="view_order", schema = "public")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private int order_id;
	
	@NotNull
	@Size(min=2,message="Name should atleast have 2 characters")
	@Column(nullable=false)
	private String name;
	
	@NotNull
	@Column(nullable=false)
	private int quantity;
	
	@NotNull
	@Column(nullable=false)
	private double price;
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id", referencedColumnName = "customerId", nullable= true)/*The @JoinColumn annotation helps Hibernate to figure out that there is a customer_id Foreign Key column in the order table that defines this association.*/
	@JsonIgnoreProperties({"orders","hibernateLazyInitializer", "handler"})
	private Customer customers;
	
  /*Default Constructor*/
	public Order() {
		super();
	}

  /*Parametarized Constructor*/
	public Order(int order_id, @NotNull @Size(min = 2, message = "Name should atleast have 2 characters") String name,
		@NotNull int quantity, @NotNull double price,@Nullable Customer customers) {
	super();
	this.order_id = order_id;
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	this.customers = customers;
}

/*getters and setters*/

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	
  /*toString() method*/
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", restaurant=" + ", customers=" + customers + "]";
	}
	
	
}
