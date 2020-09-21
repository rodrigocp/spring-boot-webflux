package br.com.rcp.gateway.security

import br.com.rcp.gateway.domains.AccountDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class Security(@Autowired private var service: AccountDetailsService) : WebSecurityConfigurerAdapter() {
	override fun configure(http: HttpSecurity?) {
		http
			?.cors()
			?.and()
			?.csrf()?.disable()
			?.formLogin()?.disable()
			?.logout()?.disable()
			?.sessionManagement()
			?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.authorizeRequests()
			?.antMatchers("/users/public")?.permitAll()
			?.antMatchers("/users/create")?.permitAll()
			?.antMatchers("/login")?.permitAll()
			?.anyRequest()?.authenticated()
	}
}