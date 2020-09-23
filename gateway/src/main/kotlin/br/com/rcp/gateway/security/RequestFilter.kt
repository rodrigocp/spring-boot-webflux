package br.com.rcp.gateway.security

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.apis.SessionService
import org.springframework.web.server.*
import reactor.core.publisher.Mono

class RequestFilter(private val service: AccountServiceAPI, private val session: SessionService) : WebFilter {
	override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
		TODO("Implement method")
//		val	token	= Optional.ofNullable(request.getHeader(AUTHORIZATION))
//		val	mapper	= ObjectMapper()
//		chain.doFilter(request, response)
	}
}