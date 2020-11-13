package br.com.rcp.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
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
			.route("account-service") { it.path("/api/users/**").filters { p -> p.rewritePath("^/api", "/external") }.uri("lb://account-service") }
			.build()
	}

	@Bean
	fun webClient() : WebClient {
//		return WebClient.create("http://service-account:8070")
		return WebClient.create("http://localhost:8070")
	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
