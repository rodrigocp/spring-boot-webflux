package br.com.services.evaluation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.web.reactive.config.EnableWebFlux
import reactor.core.publisher.Mono
import java.util.*

@ExperimentalCoroutinesApi
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableR2dbcAuditing
@EnableWebFlux
@EnableHystrix
class EvaluationApplication {
	@Bean
	fun auditorAware(): ReactiveAuditorAware<String?>? {
		return ReactiveAuditorAware {
			Mono.just(UUID.randomUUID().toString())
		}
	}
}

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<EvaluationApplication>(*args)
}
