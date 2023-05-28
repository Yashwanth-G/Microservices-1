package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/accounts/**")
						.filters(f -> f.rewritePath("/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString()))
						.uri("lb://ACCOUNTS")).
				route(p -> p
						.path("/loans/**")
						.filters(f -> f.rewritePath("/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString()))
						.uri("lb://LOANS")).
				route(p -> p
						.path("/cards/**")
						.filters(f -> f.rewritePath("/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString()))
						.uri("lb://CARDS")).
				route(p -> p
						.path("/deposits/**")
						.filters(f -> f.rewritePath("/deposits/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString()))
						.uri("lb://DEPOSITS"))
						.build();
	}
}
