package com.zara.similarproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimilarProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimilarProductsServiceApplication.class, args);
	}

}
