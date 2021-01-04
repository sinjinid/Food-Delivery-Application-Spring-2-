package com.boot.service;

import java.util.List;
import java.util.Map;

import com.boot.model.Customer;

public interface CustomerService {
	
	String saveOrUpdate(Customer customer);
	
	List<Customer> getAllCustomers();
	
	Customer loginCustomer(int customerId, String password);
	
	Customer findOneById(int customerId);
	
}
