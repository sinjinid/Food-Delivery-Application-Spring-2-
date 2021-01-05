package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sprint.beans.Customer;
import com.sprint.beans.Restaurant;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	
	Customer findByCustomerId(int customerId);
	
	@Query("select c from Customer c where c.customerId=:customerId AND c.password=:password")
	public Customer loginCustomer(@Param("customerId") int customerId,@Param("password") String password);

	public Customer findBymailId(String mailId);


}
