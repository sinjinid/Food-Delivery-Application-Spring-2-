package com.sprint.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.beans.Customer;
import com.sprint.beans.Order;
import com.sprint.exception.ResourceNotFoundException;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.MenuRepository;
import com.sprint.repository.OrderRepository;
import com.sprint.service.CustomerService;
import com.sprint.service.MenuService;
import com.sprint.service.OrderService;

@RestController
public class OrderController {

	private static final Logger logger=LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	OrderService service;

	@Autowired
	OrderRepository oRepo;

	@Autowired
	CustomerRepository cRepo;

	@Autowired
	CustomerService cService;

	//the customer can select order
	@PostMapping("/customer/selectorder/{customer_id}")
	public HashMap<String,Double> selectMenu(@PathVariable("customer_id") int customer_id, @RequestBody final Order order)throws ResourceNotFoundException {
		Customer c = 
				cRepo.findById(customer_id)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customer_id));

		order.setCustomers(c);
		HashMap<String,Double> res = service.addOrder(order);
		return res;
	}
	
	//to get particular order
	@GetMapping("order/{id}")
	public Optional<Order> allOrder(@PathVariable("id") int order_id){
		return oRepo.findById(order_id);
	}

	//to order food via customer id
	@GetMapping("/customer/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") int customerId) {
		logger.info("Order is fetched by customer id!!!");
		Customer c = cRepo.findByCustomerId(customerId);
		List<String> list = oRepo.getOrderList(customerId);
		if (list.isEmpty()) {
			return new ResponseEntity("No order for this customer ID",HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(list,HttpStatus.OK);
		}
	}

	//to view all the orders from resturant's side
	@GetMapping(value ="restaurant/ViewOrders")
	public List<Order> orderlist(){
		return oRepo.findAll();
	}


	//delete particular order
	@DeleteMapping("order/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id")int order_id) throws ResourceNotFoundException{
		Order order = oRepo.findById(order_id).orElseThrow(()
				->new ResourceNotFoundException("No order against this order id:"+ order_id));	
		//Customer c = order.getCustomers();
		order.setCustomers(null);
		oRepo.save(order);
		oRepo.delete(order);
		oRepo.flush();

		Map<String, Boolean> response = new HashMap<>();
		response.putIfAbsent("deleted", Boolean.TRUE);

		return response;
	}

	//update particular customer
	@PutMapping("/update/order/{orderId}")
	public ResponseEntity<Order> updateCustomer(@PathVariable(value="orderId")int orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException{
		Order order = oRepo.findById(orderId)
				.orElseThrow(()->new ResourceNotFoundException("Order not found for this id::"+ orderId));

		order.setName(orderDetails.getName());
		order.setPrice(orderDetails.getPrice());
		order.setQuantity(orderDetails.getQuantity());

		return ResponseEntity.ok(oRepo.saveAndFlush(order));
	}


}
