package br.com.rcp.gateway

import br.com.rcp.gateway.domains.AccountDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class Security(@Autowired private var service: AccountDetailsService): WebSecurityConfigurerAdapter() {
	@Bean
	fun encoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}

	@Bean
	override fun authenticationManagerBean(): AuthenticationManager {
		return super.authenticationManagerBean()
	}

	override fun configure(auth: AuthenticationManagerBuilder?) {
		auth?.userDetailsService(service)
	}

	override fun configure(web: WebSecurity?) {
		web?.ignoring()?.antMatchers(HttpMethod.OPTIONS, "/**")
	}
}