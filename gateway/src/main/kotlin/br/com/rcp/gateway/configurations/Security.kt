package br.com.rcp.gateway.configurations

import br.com.rcp.gateway.apis.SessionServiceAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class Security(private val service: SessionServiceAPI) {
	@Bean
	fun securityWebFilterChain(security: ServerHttpSecurity) : SecurityWebFilterChain {
		return security
			.cors()
			.and()
			.csrf().disable()
			.formLogin().disable()
			.logout().disable()
			.authorizeExchange()
			.pathMatchers("/api/login/**").permitAll()
			.anyExchange().access(AuthorizationManager(service))
			.and()
			.build()
	}

	class AuthorizationManager(private val service: SessionServiceAPI) : ReactiveAuthorizationManager<AuthorizationContext> {
		override fun check(authentication: Mono<Authentication>?, context: AuthorizationContext?): Mono<AuthorizationDecision> {
			return Mono
				.just(context!!)
				.flatMap { Mono.just(it.exchange!!) }
				.flatMap { Mono.just(it.request.headers.getFirst(AUTHORIZATION)?.replace("^Bearer\\s+".toRegex(), "") ?: "") }
				.flatMap { service.find(it) }
				.flatMap { Mono.just(AuthorizationDecision(true)) }
				.doOnError { it.printStackTrace() }
				.onErrorReturn(AuthorizationDecision(false))

//			return mono {
//				val	unauthorized	= AuthorizationDecision(false)
//				val exchange		= context?.exchange ?: return@mono unauthorized
//				val	token			= exchange.request.headers.getFirst(AUTHORIZATION)?.replace("^Bearer\\s+".toRegex(), "") ?: return@mono unauthorized
//				return@mono unauthorized
//			}
		}
	}

//	class RequestFilter(private val service: SessionServiceAPI) : WebFilter {
//		override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
//
//			return chain.filter(exchange)
//		}
//
//		private fun authenticate(exchange: ServerWebExchange) : Mono<Authentication> {
//			return Mono.just(exchange)
//				.flatMap { Mono.justOrEmpty(it.request.headers[AUTHORIZATION]) }
//				.flatMap { Mono.justOrEmpty(it[0].replace("^Bearer\\s+".toRegex(), "")) }
//				.flatMap { service.find(it) }
//				.flatMap { Mono.justOrEmpty(UsernamePasswordAuthenticationToken(it?.data, null, (it?.data?.get("roles") as? List<Any?>)?.map {role -> SimpleGrantedAuthority(role as String)})) }
//		}
//
//		private fun setAuthentication(authentication: Mono<Authentication>) : Mono<Unit> {
//			return authentication.flatMap {
//				ReactiveSecurityContextHolder.getContext().map { context ->
//					context.authentication = it
//				}
//			}
//		}
//	}
}