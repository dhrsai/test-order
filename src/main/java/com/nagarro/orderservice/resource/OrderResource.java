package com.nagarro.orderservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.orderservice.model.Order;
import com.nagarro.orderservice.model.OrderResponse;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping("orders/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrder(id);
    }
    
    @RequestMapping("orders/user/{id}")
    public OrderResponse getOrderByUser(@PathVariable int id) {
        return orderService.getOrderByUser(id);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/orders")
	public void addOrder(@RequestBody Order order) {
		orderService.addOrder(order);
	}
    
    @RequestMapping(method=RequestMethod.PUT, value="/orders/{id}")
	public void updateUser(@RequestBody Order order, @PathVariable int id) {
    	orderService.updateOrder(id, order);
	}
    
    @RequestMapping(method=RequestMethod.DELETE, value="/orders/{id}")
	public void deleteUser(@PathVariable int id) {
    	orderService.deleteOrder(id);
	}
}