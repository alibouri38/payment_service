package com.sqli.microservice.services;

import java.util.List;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sqli.microservice.components.OrderDetails;
import com.sqli.microservice.entities.City;
import com.sqli.microservice.entities.Country;
import com.sqli.microservice.entities.Order;
import com.sqli.microservice.entities.State;
import com.sqli.microservice.repositories.CityRepository;
import com.sqli.microservice.repositories.CountryRepository;
import com.sqli.microservice.repositories.OrderRepository;
import com.sqli.microservice.repositories.StateRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    public OrderService(OrderRepository orderRepository ,
    		CountryRepository countryRepository,
    		StateRepository stateRepository , 
    		CityRepository cityRepository) {
        this.orderRepository = orderRepository;
        this.countryRepository  = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;        
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    CompletableFuture<String> future = new CompletableFuture<>();
    public List<OrderDetails> getAllOrders() {
    	System.out.println("@@@ product-details-request @@@");
        try {
            kafkaTemplate.send("product-details-request", "1");
            String js = future.get(10, TimeUnit.SECONDS);
            return orderRepository.findAll().stream().map(s -> new OrderDetails(s, js)).toList();
        } catch (Exception e) {
            return null;
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);
        if (existingOrder == null) {
            return null;
        }
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setOrderItems(order.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    

    public List<Country> listCountries() {
        return countryRepository.findAll();
    }

    
    public Iterable<State> listStates() {
        return stateRepository.findAll();
    }
    
  
    public List<State> findByCountryCode(String code) {
        return stateRepository.findByCountryCode(code);
    }
    
    public List<City> findByStateId(Long id) {
    	System.out.println("@@@ State Id @@@" +id);
    	//return null;
        try {
			return cityRepository.findByCities(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    @KafkaListener(topics = "product-details-response", groupId = "group-3")
    public void receiveProductDetailsResponse(String json) {
        future.complete(json);
        System.out.println("json = " + json);
    }
    
}
