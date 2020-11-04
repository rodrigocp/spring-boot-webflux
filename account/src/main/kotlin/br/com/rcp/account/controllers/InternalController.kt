package br.com.rcp.account.controllers

import br.com.rcp.account.handlers.internal.AccountHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class InternalController {
	@Bean("internalAccountController")
	fun internalAccountHandler(@Autowired handler: AccountHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/internal/users/",								handler::fetch)
				GET("/internal/users/{identifier}",					handler::find)
				GET("/internal/users/find-by-username/{username}",	handler::findByUsername)
				POST("/internal/users/",							handler::persist)
				PATCH("/internal/users/{identifier}",				handler::update)
			}
		}
	}
}