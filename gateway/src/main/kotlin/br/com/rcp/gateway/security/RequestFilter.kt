package br.com.rcp.gateway.security

import br.com.rcp.gateway.apis.AccountService
import br.com.rcp.gateway.apis.SessionService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestFilter(private val service: AccountService, private val session: SessionService) : OncePerRequestFilter() {
	override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
		val	token	= Optional.ofNullable(request.getHeader(AUTHORIZATION))
		val	mapper	= ObjectMapper()
		chain.doFilter(request, response)
	}
}