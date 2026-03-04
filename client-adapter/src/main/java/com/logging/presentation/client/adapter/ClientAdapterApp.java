package com.logging.presentation.client.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientAdapterApp {

	public static void main(String[] args) {
		SpringApplication.run(ClientAdapterApp.class, args);
	}

}
