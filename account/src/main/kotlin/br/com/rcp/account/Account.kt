package br.com.rcp.account

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.config.EnableWebFlux

@ExperimentalCoroutinesApi
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableMongoAuditing
@EnableWebFlux
@EnableHystrix
class Authentication {
	@Bean
	fun encoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<Authentication>(*args)
}