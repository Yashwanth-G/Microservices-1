package com.example.deposits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class DepositsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositsApplication.class, args);
	}

}
