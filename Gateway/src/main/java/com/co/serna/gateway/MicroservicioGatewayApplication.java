package com.co.serna.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioGatewayApplication.class, args);
	}

}
