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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.exception.CustomerRestaurantException;
import com.sprint.exception.ResourceNotFoundException;
import com.sprint.beans.Customer;
import com.sprint.beans.Menu;
import com.sprint.beans.Restaurant;
import com.sprint.repository.MenuRepository;
import com.sprint.repository.OrderRepository;
import com.sprint.repository.RestaurantRepository;
import com.sprint.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	private static final Logger logger=LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	RestaurantRepository rRepo;
	
	@Autowired
	RestaurantService rService;
	
	
	
	//Restaurant registration
	
	@PostMapping("/Register")
	private String saveRestaurant(@Valid @RequestBody Restaurant restaurant) throws CustomerRestaurantException {
		if (restaurant.getRid()> 0) {
			if (restaurant.getRestaurantName()!= null && restaurant.getRestaurantAddress() != null
					&& restaurant.getRestaurantEmail() != null && restaurant.getRestaurantPwd() != null)
				return restaurant.getRid() + " " + rService.saveOrUpdate(restaurant);
			else {
				logger.error("Exception Occured!!! Restaurant field has incorrect data");
				throw new CustomerRestaurantException(
						" Exception Occured!!! INVALID Details!!!Please Check Details");
			}
		} else {
			logger.error("Exception Occured!!! Restaurant field has incoreect data");
			throw new CustomerRestaurantException(" Exception Occured!!! INVALID ID!!!Please Check rid");
		}
		
	}
	
	
	// Restaurant login
	//Url Example:  http://localhost:9095/restaurant/login?customerId=2&password=Juhi@8080
	@PostMapping("/login")
	public ResponseEntity<Restaurant> loginUser(@Valid @RequestParam("rid") int rid, @Valid @RequestParam("restaurantPwd") String restaurantPwd) {
			logger.info("Restaurant Login Method Started!");
			Restaurant restaurant = rService.loginRestaurant(rid, restaurantPwd);
			if (restaurant != null) {
				return new ResponseEntity("Login Successfull!!!", HttpStatus.OK);
			}
			return new ResponseEntity("Login Failed!!!", HttpStatus.BAD_REQUEST);

	}
	
	
	//To get all restaurant
	
	@GetMapping("/getRestaurant")
	private List<Restaurant> getAllRestaurant() {
		logger.info("Restaurant Retrived");
		return rService.getAllRestaurant();
	}


	//To get restaurant by Id
	
	@GetMapping("/profile/{rid}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("rid") int rid, Restaurant restaurant) {
		logger.info("Restaurant is fetched by restaurant id!!!");
		if (restaurant.getRid() == rid) {
			return ResponseEntity.ok(rService.findOneById(rid));
		} else {
			return ResponseEntity.badRequest().build();
			}
		}
	
	
	//restaurant logout
	 @GetMapping("/logout")
	    public String exit() {
	    	return "You have been logged out";
	    }
	
	
	//delete particular restaurant
		 @DeleteMapping("/delete/{rid}")
			public Map<String, Boolean> delete(@PathVariable(value="rid")int rid) throws ResourceNotFoundException{
				Restaurant restaurant = rRepo.findById(rid)
						.orElseThrow(()->new ResourceNotFoundException("Restaurant not found for this id::"+ rid));
				
				this.rRepo.delete(restaurant);
				
				Map<String, Boolean> response = new HashMap<>();
				response.putIfAbsent("deleted", Boolean.TRUE);
				
				return response;
			}
		 
		 @PutMapping("/update/{rid}")
			public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value="rid")int rid,
					@Valid @RequestBody Restaurant res) throws ResourceNotFoundException{
				Restaurant restaurant = this.rRepo.findById(rid)
						.orElseThrow(()->new ResourceNotFoundException("Restaurant not found for this id::"+ rid));
				
			
				restaurant.setRestaurantEmail(res.getRestaurantEmail());
				restaurant.setRestaurantName(res.getRestaurantName());
				restaurant.setRestaurantPwd(res.getRestaurantPwd());
				restaurant.setRestaurantAddress(res.getRestaurantAddress());
				
				return ResponseEntity.ok(this.rRepo.saveAndFlush(restaurant));
			}
	
}
