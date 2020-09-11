package br.com.rcp.gateway.security

import br.com.rcp.gateway.domains.AccountDetailsService
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.filter.*
import org.keycloak.adapters.springsecurity.management.HttpSessionManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class Security(@Autowired private var service: AccountDetailsService): KeycloakWebSecurityConfigurerAdapter() {
	override fun configure(http: HttpSecurity?) {
		http
			?.cors()?.and()?.csrf()?.disable()?.sessionManagement()
			?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.authorizeRequests()
			?.antMatchers("/users/public")?.permitAll()
			?.antMatchers("/users/create")?.permitAll()
			?.antMatchers("/users/signin")?.permitAll()
			?.anyRequest()?.authenticated()
	}

	override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
		return ChangeSessionIdAuthenticationStrategy()
	}

	@Autowired
	fun global(builder: AuthenticationManagerBuilder) {
		keycloakAuthenticationProvider().apply {
			setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
			builder.authenticationProvider(this)
		}
	}

	@Bean
	fun AuthenticationProcessingFilter(filter: KeycloakAuthenticationProcessingFilter): FilterRegistrationBean<*>? {
		return FilterRegistrationBean(filter).apply {
			isEnabled = true
		}
	}

	@Bean
	fun preAuthorizationActionsFilters(filter: KeycloakPreAuthActionsFilter): FilterRegistrationBean<*>? {
		return FilterRegistrationBean(filter).apply {
			isEnabled = false
		}
	}

	@Bean
	fun authenticatedActionsFilters(filter: KeycloakAuthenticatedActionsFilter): FilterRegistrationBean<*>? {
		return FilterRegistrationBean(filter).apply {
			isEnabled = false
		}
	}

	@Bean
	fun contextRequestFilters(filter: KeycloakSecurityContextRequestFilter): FilterRegistrationBean<*>? {
		return FilterRegistrationBean(filter).apply {
			isEnabled = false
		}
	}

	@Bean
	@ConditionalOnMissingBean(HttpSessionManager::class)
	override fun httpSessionManager(): HttpSessionManager? {
		return HttpSessionManager()
	}
}