package com.logging.presentation.consumer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ConsumerServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApp.class, args);
	}

}
