package com.sprint.service;

import java.util.HashMap;

import com.sprint.beans.Menu;
import com.sprint.beans.Order;

public interface OrderService {

	public HashMap<String,Double> addOrder(Order order);
	public HashMap<String,Double> selectOrder(String foodName,int count);
	
}
