package br.com.rcp.gateway

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
class Gateway {
	@Bean
	fun keycloakConfigResolver(): KeycloakSpringBootConfigResolver? {
		return KeycloakSpringBootConfigResolver()
	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
