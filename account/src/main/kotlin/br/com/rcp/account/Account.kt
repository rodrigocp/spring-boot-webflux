package br.com.rcp.account

import br.com.rcp.account.handlers.AccountHandler
import br.com.rcp.account.handlers.AuthenticationHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.http.MediaType
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.*

@ExperimentalCoroutinesApi
@SpringBootApplication
@EnableMongoAuditing
@EnableWebFlux
class Authentication {
	@Bean
	fun accountRouter(@Autowired handler: AccountHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/accounts/",					handler::fetch)
				GET("/accounts/{identifier}",		handler::find)
				POST("/accounts/",					handler::persist)
				PATCH("/accounts/{identifier}",		handler::update)
			}
		}
	}

	@Bean
	fun authenticationRouter(@Autowired handler: AuthenticationHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/authentication/find-by-username/{username}",	handler::findByUsername)
			}
		}
	}

	@Bean
	fun encoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}

@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
	runApplication<Authentication>(*args)
}