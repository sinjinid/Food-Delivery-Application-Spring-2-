package com.sprint.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.exception.CustomerRestaurantException;
import com.sprint.beans.Customer;
import com.sprint.beans.Restaurant;
import com.sprint.repository.CustomerRepository;
import com.sprint.service.CustomerService;
import com.sprint.exception.ResourceNotFoundException;
/**
 * this is controller class for Customer.
 * 
 * @author 
 *
 */

@RestController
public class CustomerController {
	private static final Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository cRepo;
	
	@Autowired
	CustomerService customerService;
	/**
	 * @param Customer
	 * @return
	 * @throws CustomerRestaurantException 
	 * @throws Exception
	 */
	
	//Customer registration
	

		
	@PostMapping("/Register")
	private String saveCustomer(@Valid @RequestBody Customer customer) throws CustomerRestaurantException {
		if (customer.getCustomerId() > 0) {
			if (customer.getFirstName() != null && customer.getLastName() != null && customer.getAddress() != null
					&& customer.getMailId() != null && customer.getPassword() != null || customer.getMobileNo() != null)
				return customer.getCustomerId() + " " + customerService.saveOrUpdate(customer);
			else {
				logger.error("Exception Occured!!! Customer field has incorrect data");
				throw new CustomerRestaurantException(
						" Exception Occured!!! INVALID Details!!!Please Check Details");
			}
		} else {
			logger.error("Exception Occured!!! Customer field has incoreect data");
			throw new CustomerRestaurantException(" Exception Occured!!! INVALID ID!!!Please Check customerId");
		}
		
	}
	
	
	// Customer login
	//http://localhost:8085/customer/login?customerId=2&password=Juhi@8080
	@PostMapping("customer/login")
	public ResponseEntity<Customer> loginUser(@Valid @RequestParam("customerId") int customerId, @Valid @RequestParam("password") String password) {
		logger.info("Customer Login Method Started!");
		Customer customer = customerService.loginCustomer(customerId, password) ;
		if (customer != null) {
			return new ResponseEntity("Login Successfull!!!", HttpStatus.OK);
		}
		return new ResponseEntity("Login Failed!!!", HttpStatus.BAD_REQUEST);

}
	
	
	//To get all customers
	
	@GetMapping("/getCustomers")
	private List<Customer> getAllCustomers() {
		logger.info("Customers Retrived");
		return customerService.getAllCustomers();
	}


	//To get customers by Id
	
	@GetMapping("/profile/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId, Customer customer) {
		logger.info("Customer is fetched by customer id!!!");
		if (customer.getCustomerId() == customerId) {
			return ResponseEntity.ok(customerService.findOneById(customerId));
		} else {
			return ResponseEntity.badRequest().build();
			}
		}
	
	
	//customer logout
	 @GetMapping("/logout")
	    public String exit() {
	    	return "You have been logged out";
	    }

	 //delete particular customer
	 @DeleteMapping("/delete/{customerId}")
		public Map<String, Boolean> delete(@PathVariable(value="customerId")int customerId) throws ResourceNotFoundException{
			Customer customer = cRepo.findById(customerId)
					.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customerId));
			
			this.cRepo.delete(customer);
			
			Map<String, Boolean> response = new HashMap<>();
			response.putIfAbsent("deleted", Boolean.TRUE);
			
			return response;
		}
	 
	 //update particular customer
	 @PutMapping("/updateCustomer/{customerId}")
		public ResponseEntity<Customer> updateCustomer(@PathVariable(value="customerId")int customerId,
				@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException{
			Customer customer = this.cRepo.findById(customerId)
					.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customerId));
			
			customer.setMailId(customerDetails.getMailId());
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setAddress(customerDetails.getAddress());
			customer.setMobileNo(customerDetails.getMobileNo());
			customer.setPassword(customerDetails.getPassword());
			
			return ResponseEntity.ok(this.cRepo.saveAndFlush(customer));
		}
	
}







