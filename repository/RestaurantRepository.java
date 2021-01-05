package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.beans.Customer;
import com.sprint.beans.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer>{
	
	Restaurant findByrid(int rid);
	
	@Query("select r from Restaurant r where r.rid=:rid AND r.restaurantPwd=:restaurantPwd")
	public Restaurant loginRestaurant(@Param("rid") int rid,@Param("restaurantPwd") String restaurantPwd);
	
	public Restaurant findByrestaurantEmail(String email); 

}
