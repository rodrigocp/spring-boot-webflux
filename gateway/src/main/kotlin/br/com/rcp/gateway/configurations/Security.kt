package br.com.rcp.gateway.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class Security {
	@Bean
	fun securityWebFilterChain(security: ServerHttpSecurity) : SecurityWebFilterChain {
		return security
			.cors()
			.and()
			.csrf().disable()
			.formLogin().disable()
			.logout().disable()
			.authorizeExchange()
			.pathMatchers("/users/public").permitAll()
			.pathMatchers("/users/create/").permitAll()
			.pathMatchers("/login/**").permitAll()
			.anyExchange().authenticated()
			.and()
			.build()
	}
}