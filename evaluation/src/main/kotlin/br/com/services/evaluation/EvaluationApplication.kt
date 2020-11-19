package br.com.services.evaluation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.reactive.config.EnableWebFlux

@ExperimentalCoroutinesApi
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableWebFlux
@EnableHystrix
class EvaluationApplication

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<EvaluationApplication>(*args)
}
