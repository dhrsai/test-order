package com.nagarro.orderservice.model;

public class Order {
    private int id;
    private int customerId;
    private String name;
    
    public Order() {
    	
    }

    public Order(int id, int customerId, String name) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}