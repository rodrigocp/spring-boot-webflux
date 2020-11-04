package br.com.rcp.gateway

import br.com.rcp.gateway.apis.AccountServiceAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import reactivefeign.webclient.WebReactiveFeign

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
	fun accountServiceAPI() : AccountServiceAPI {
		return WebReactiveFeign.builder<AccountServiceAPI>().target(AccountServiceAPI::class.java, "http://service-account:8070")
	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
