package com.sqli.microservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApp.class, args);
    }
    
    
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { String[] allowDomains = new
	 * String[2]; allowDomains[0] = "http://localhost:4200"; allowDomains[1] =
	 * "http://localhost:8080";
	 * 
	 * System.out.println("CORS configuration...."); return new WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("*"); } }; }
	 */
}