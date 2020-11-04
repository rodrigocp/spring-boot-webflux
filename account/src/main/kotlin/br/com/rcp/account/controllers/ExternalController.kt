package br.com.rcp.account.controllers

import br.com.rcp.account.handlers.external.AccountHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ExternalController {
	@Bean("externalAccountController")
	fun externalAccountController(@Autowired handler: AccountHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/external/users/",								handler::fetch)
				GET("/external/users/{identifier}",					handler::find)
				GET("/external/users/find-by-username/{username}",	handler::findByUsername)
				POST("/external/users/",							handler::persist)
				PATCH("/external/users/{identifier}",				handler::update)
			}
		}
	}
}