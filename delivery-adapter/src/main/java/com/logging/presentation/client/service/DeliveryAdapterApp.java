package com.logging.presentation.client.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DeliveryAdapterApp {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryAdapterApp.class, args);
	}

}
