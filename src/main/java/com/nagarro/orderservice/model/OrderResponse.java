package com.nagarro.orderservice.model;

import java.util.ArrayList;
import java.util.List;

 

public class OrderResponse {
    
    private int userId;
    private String name;
    private List<Order> orderList;
    
    public OrderResponse() {
    	this.orderList = new ArrayList<Order>();    	
    }
    

    
    public OrderResponse(int userId, String name) {
        super();
        this.userId = userId;
        this.name = name;
        this.orderList = new ArrayList<Order>();
    }

 

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Order> getOrder() {
        return this.orderList;
    }
    
    public void addOrder(Order order) {
        this.orderList.add(order);
    }
}