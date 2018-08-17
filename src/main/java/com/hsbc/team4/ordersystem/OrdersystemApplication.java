package com.hsbc.team4.ordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Kevin
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class OrdersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersystemApplication.class, args);
	}
}
