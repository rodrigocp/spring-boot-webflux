package br.com.rcp.gateway.configurations

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.apis.SessionServiceAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.server.*
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class Security(private val service: AccountServiceAPI, private val session: SessionServiceAPI) {
	@Bean
	fun securityWebFilterChain(security: ServerHttpSecurity) : SecurityWebFilterChain {
		return security
			.cors()
			.and()
			.csrf().disable()
			.formLogin().disable()
			.logout().disable()
			.addFilterAt(RequestFilter(session), SecurityWebFiltersOrder.AUTHORIZATION)
			.authorizeExchange()
			.pathMatchers("/api/**").authenticated()
			.anyExchange().permitAll()
			.and()
			.build()
	}

	class RequestFilter(private val service: SessionServiceAPI) : WebFilter {
		override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
			return chain.filter(exchange)
		}

		private fun authenticate(exchange: ServerWebExchange) : Mono<Authentication> {
			return Mono.just(exchange)
				.flatMap { Mono.justOrEmpty(it.request.headers[AUTHORIZATION]) }
				.flatMap { Mono.justOrEmpty(it[0].replace("^Bearer\\s+".toRegex(), "")) }
				.flatMap { service.find(it) }
				.flatMap { Mono.justOrEmpty(UsernamePasswordAuthenticationToken(it?.data, null, (it?.data?.get("roles") as? List<Any?>)?.map {role -> SimpleGrantedAuthority(role as String)})) }
		}

		private fun setAuthentication(authentication: Mono<Authentication>) : Mono<Unit> {
			return authentication.flatMap {
				ReactiveSecurityContextHolder.getContext().map { context ->
					context.authentication = it
				}
			}
		}
	}
}