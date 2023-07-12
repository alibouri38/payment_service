package com.sqli.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.microservice.entities.Order;

 
public interface OrderRepository extends JpaRepository<Order, Long> {
}
