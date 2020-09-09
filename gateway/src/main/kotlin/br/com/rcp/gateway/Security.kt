package br.com.rcp.gateway

import br.com.rcp.gateway.clients.AccountClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class Security: WebSecurityConfigurerAdapter() {
	@Autowired
	private lateinit var client: AccountClient

	override fun configure(http: HttpSecurity?) {
		http
			?.csrf()?.disable()
			?.logout()?.disable()
			?.formLogin()?.disable()
			?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			?.and()
			?.addFilterAfter(Filter(client), UsernamePasswordAuthenticationFilter::class.java)
			?.authorizeRequests()?.anyRequest()?.permitAll()
	}
}