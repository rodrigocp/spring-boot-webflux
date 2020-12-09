package br.com.rcp.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication(exclude = [MongoReactiveAutoConfiguration::class, MongoReactiveDataAutoConfiguration::class])
@EnableDiscoveryClient
class Gateway {
	@Bean
	fun locator(builder: RouteLocatorBuilder): RouteLocator {
		return builder.routes()
			.route("account-service")	{ it.path("/api/accounts/**").filters	{filter -> filter.rewritePath("^/api", "") }.uri("lb://account-service") }
			.route("vehicle-service")	{ it.path("/api/vehicles/**").filters 	{filter -> filter.rewritePath("^/api", "") }.uri("lb://vehicles-service") }
			.build()
	}

	@Bean
	@LoadBalanced
	fun webClientBuilder() : WebClient.Builder {
		return WebClient.builder()
	}

	@Bean
	fun accountWebClient(builder: WebClient.Builder) : WebClient {
		return builder.baseUrl("http://account-service:8080").build()
	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
