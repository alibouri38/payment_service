package com.sqli.microservice.dto;

import com.sqli.microservice.entities.Customer;
import com.sqli.microservice.entities.Order;
import com.sqli.microservice.entities.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	private Customer customer;
    public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	private Order order;
    private String shippingAddress;
    private String billingAddress;
    private Set<OrderItem> orderItems;

}
