package br.com.rcp.gateway.configurations

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class Interceptor : GlobalFilter {
	private	val	identifier	= "x-account-identifier"
	private	val	roles		= "x-account-roles"

	override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain) : Mono<Void> {
		return exchange.getPrincipal<Authentication>().flatMap { chain.filter(mutate(exchange, it)) }
	}

	private fun mutate(exchange: ServerWebExchange, authentication: Authentication): ServerWebExchange {
		return exchange.mutate().request {
			it.header(identifier,	authentication.name)
			it.header(roles,		authentication.authorities?.joinToString("|") ?: "")
		}.build()
	}
}