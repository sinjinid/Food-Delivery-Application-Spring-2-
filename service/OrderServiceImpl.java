package com.sprint.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.beans.Customer;
import com.sprint.beans.Menu;
import com.sprint.beans.Order;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.MenuRepository;
import com.sprint.repository.OrderRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService{

	static HashMap<String,Double> data = new HashMap<String,Double>();
	@Autowired
	OrderRepository oRepo;
	@Autowired
	MenuRepository mRepo;
	
	
	@Override
	public HashMap<String,Double> addOrder(Order order) {
		oRepo.saveAndFlush(order);
		String foodname = order.getName();
		double foodprice =order.getPrice();
		int foodquantity = order.getQuantity();
		double total = foodprice * foodquantity;
		HashMap<String,Double> res= new HashMap<String,Double>();
		res.put(foodname, total);
		return res;
		
	}
	
}
