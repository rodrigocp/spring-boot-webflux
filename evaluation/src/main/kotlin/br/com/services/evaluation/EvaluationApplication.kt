package br.com.services.evaluation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.web.reactive.config.EnableWebFlux
import reactor.core.publisher.Mono
import java.util.*

@SpringBootApplication
@EnableDiscoveryClient
@EnableR2dbcAuditing
@EnableWebFlux
class EvaluationApplication {
	@Bean
	fun auditorAware(): ReactiveAuditorAware<UUID> {
		return ReactiveAuditorAware {
			Mono.just(UUID.randomUUID())
		}
	}
}

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<EvaluationApplication>(*args)
}
