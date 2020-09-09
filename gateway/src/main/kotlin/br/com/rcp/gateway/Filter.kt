package br.com.rcp.gateway

import br.com.rcp.gateway.clients.AccountClient
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Filter(private val client: AccountClient): OncePerRequestFilter() {
	override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
//		client.find("administrator")
		chain.doFilter(request, response)
	}
}