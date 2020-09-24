package br.com.rcp.gateway.security

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.apis.SessionServiceAPI
import org.springframework.web.server.*
import reactor.core.publisher.Mono

class RequestFilter(private val service: AccountServiceAPI, private val session: SessionServiceAPI) : WebFilter {
	override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
		TODO("Implement method")
//		val	token	= Optional.ofNullable(request.getHeader(AUTHORIZATION))
//		val	mapper	= ObjectMapper()
//		chain.doFilter(request, response)
	}
}