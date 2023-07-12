package com.sqli.microservice.iservice;

import com.sqli.microservice.dto.PaymentInfo;

import com.sqli.microservice.dto.Purchase;
import com.sqli.microservice.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
