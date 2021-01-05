package com.sprint.service;


import java.util.List;

import com.sprint.beans.Restaurant;

public interface RestaurantService {
String saveOrUpdate(Restaurant restaurant);
	
	List<Restaurant> getAllRestaurant();
	
	Restaurant loginRestaurant(int rid, String restaurantPwd);
	
	Restaurant findOneById(int rid);
}
