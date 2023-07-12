package com.sqli.microservice.controllers;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.microservice.components.OrderDetails;
import com.sqli.microservice.dto.PaymentInfo;
import com.sqli.microservice.dto.Purchase;
import com.sqli.microservice.dto.PurchaseResponse;
import com.sqli.microservice.entities.City;
import com.sqli.microservice.entities.Country;
import com.sqli.microservice.entities.Order;
import com.sqli.microservice.entities.State;
import com.sqli.microservice.iservice.CheckoutService;
import com.sqli.microservice.services.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/payments")
public class OrderController {
    private  OrderService orderService;
    private  CheckoutService checkoutService;
    public OrderController(OrderService orderService,CheckoutService checkoutService) {
        this.orderService = orderService;
        this.checkoutService = checkoutService;
    }
    
   

    @GetMapping("")
    public List<OrderDetails> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order product) {
        return orderService.createOrder(product);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order product) {
        return orderService.updateOrder(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
    

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
    	System.out.println("@@@@@ Debut traitement place Order @@@@@" +purchase.toString());
    	PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        System.out.println("@@@@@ Fin traitement place Order @@@@@" +purchaseResponse.toString());
        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
    	System.out.println("###### Debut traitement create Payment Intent ######" +paymentInfo.toString());
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();
        System.out.println("###### Fin traitement createPaymentIntent ######" +paymentStr);
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
    
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        try {
        	System.out.println("debut traitement liste des countries +++");
			return new ResponseEntity<>(orderService.listCountries(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @GetMapping("/states")
    public Iterable<State> getAllStates() {
        return orderService.listStates();
    }
    
    @GetMapping("/states/search/findByCountryCode/{code}")
    public List<State> findByCountryCode(@Param("code") String code) {
    	System.out.println("Lister les country par code");
        return orderService.findByCountryCode(code);
    }
    
    @GetMapping("/cities/search/findByStateId/{id}")
    public List<City> findByStateId(@PathVariable Long id) {
    	System.out.println("FindByStateId ++++ "  + id );
        return orderService.findByStateId(id);
    }
}
