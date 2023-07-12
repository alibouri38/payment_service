package com.sqli.microservice.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
   private final String orderTrackingNumber;

public PurchaseResponse(String orderTrackingNumber) {
	super();
	this.orderTrackingNumber = orderTrackingNumber;
}

}
