package com.nagarro.orderservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.orderservice.model.Order;
import com.nagarro.orderservice.model.OrderResponse;
import com.nagarro.orderservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	public RestTemplate restTemplate;

	private List<Order> orders = new ArrayList<>(
			Arrays.asList(new Order(1, 1, "Product A"), new Order(2, 1, "Product B"), new Order(3, 2, "Product C"),
					new Order(4, 1, "Product D"), new Order(5, 2, "Product E")));

	public List<Order> getAllOrders() {
		return orders;
	}

	public Order getOrder(int id) {
		return orders.stream().filter(o -> o.getId() == id).findFirst().get();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void updateOrder(int id, Order order) {
		for (int i = 0; i < orders.size(); i++) {
			Order o = orders.get(i);
			if (o.getId() == id) {
				orders.set(i, order);
				return;
			}
		}
	}

	public void deleteOrder(int id) {
		orders.removeIf(u -> u.getId() == id);
	}

	@HystrixCommand(fallbackMethod = "getFallbackService")
	public OrderResponse getOrderByUser(int id) {
		List<Order> ord = new ArrayList<Order>();
		OrderResponse res = new OrderResponse();
		for (int i = 0; i < orders.size(); i++) {
			Order o = orders.get(i);
			if (o.getId() == id) {
				User user = restTemplate.getForObject("localhost:9090/orders/user/" + o.getId(), User.class);
				res.addOrder(o);
				res.setName(user.getName());
			}
		}
		return res;
	}

	public OrderResponse getFallbackService(int id) {
		return new OrderResponse(1, "User-1");
	}
}
