package br.com.rcp.gateway.auth

import org.springframework.context.annotation.Configuration

@Configuration
class AuthServerConfiguration {
	companion object {
		val	resource_id	= "api"
	}

//	@Bean
//	@Primary
//	fun tokenServices(): DefaultTokenServices {
//		return DefaultTokenServices().apply {
//			setSupportRefreshToken(true)
//			setTokenStore()
//		}
//	}
}