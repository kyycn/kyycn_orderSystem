package com.hsbc.team4.ordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kevin
 */
@SpringBootApplication
@EnableAutoConfiguration
public class OrdersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersystemApplication.class, args);
	}
}
