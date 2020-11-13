package br.com.rcp.gateway.configurations

import br.com.rcp.gateway.apis.AccountServiceAPI
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.*
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class Security {
	@Bean
	fun securityWebFilterChain(security: ServerHttpSecurity) : SecurityWebFilterChain {
		return security
			.cors().and()
			.csrf().disable()
			.formLogin().disable()
			.logout().disable()
			.httpBasic()
			.and().authorizeExchange()
			.anyExchange().authenticated()
			.and().securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
			.build()
	}

	@Component
	class SecurityUserDetailService(private val service: AccountServiceAPI) : ReactiveUserDetailsService {
		override fun findByUsername(username: String?): Mono<UserDetails> {
			return mono {
				service.find(username ?: "").awaitFirstOrNull()?.let {
					User(it.username, it.password, it.roles.map {role -> SimpleGrantedAuthority(role) })
				}
			}
		}
	}
}