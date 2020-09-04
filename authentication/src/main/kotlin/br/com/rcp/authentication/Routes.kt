package br.com.rcp.authentication

import br.com.rcp.authentication.handlers.AccountHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.*

@Configuration
class Routes {
	@Bean
	fun account(@Autowired handler: AccountHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(APPLICATION_JSON).nest {
				GET("/users/", handler::fetch)
				GET("/users/{identifier}", handler::find)
			}
		}
	}
}