package com.example.deposits;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@OpenAPIDefinition(
		info = @Info(
				title = "Deposits Service REST APIs",
				description = "Deposits Service REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name="Yashwanth Gundlapally",
						email="g.yashwanth896@gmail.com",
						url="www.imgyash.com"
				),
				license = @License(
						name="Apache 2.0",
						url="www.imgyash.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Loans Service Doc",
				url = "www.imgyash.com"
		)
)
public class DepositsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositsApplication.class, args);
	}

}
