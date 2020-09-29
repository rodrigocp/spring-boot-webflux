package br.com.rcp.account

import br.com.rcp.account.handlers.AccountHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.http.MediaType
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.*

@ExperimentalCoroutinesApi
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableMongoAuditing
@EnableWebFlux
@EnableHystrix
class Authentication {
	@Bean
	fun account(@Autowired handler: AccountHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/users/",				handler::fetch)
				GET("/users/{identifier}",	handler::find)
				GET("/users/{username}",	handler::findByUsername)
				POST("/users/",				handler::persist)
				POST("/users/validate/",	handler::validate)
				PATCH("/users/{identifier}",handler::update)
			}
		}
	}

	@Bean
	fun encoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<Authentication>(*args)
}