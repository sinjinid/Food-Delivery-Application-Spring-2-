package com.sprint.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Menu implements Serializable{
	
	@Id
	private Long m_id;
	
	private String foodName;

	private double foodPrice;



	public Menu() {}

	
	

	

	

	public Menu(Long m_id, String foodName, double foodPrice) {
		super();
		this.m_id = m_id;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}








	public Long getM_id() {
		return m_id;
	}

	public void setM_id(Long m_id) {
		this.m_id = m_id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	
	@Override
	public String toString() {
		return "Menu [m_id=" + m_id + ", foodName=" + foodName + ", foodPrice=" + foodPrice 
				+ "]";
	}
	
	

}
