package com.sqli.microservice.components;

import com.sqli.microservice.entities.Order;

public record OrderDetails (Order order, String productName){}
