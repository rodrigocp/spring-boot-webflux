package br.com.rcp.session

import br.com.rcp.session.domain.Session
import br.com.rcp.session.handlers.SessionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.*
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@SpringBootApplication
@EnableRedisRepositories
class SessionApplication {
	@Bean
	fun session(@Autowired handler: SessionHandler): RouterFunction<ServerResponse> {
		return coRouter {
			accept(MediaType.APPLICATION_JSON).nest {
				GET("/sessions/{token}",	handler::find)
				POST("/sessions/",			handler::persist)
				DELETE("/sessions/{token}",	handler::remove)
			}
		}
	}

	@Bean
	fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory?): ReactiveRedisTemplate<String, Session>? {
		val	kSerializer	= StringRedisSerializer()
		val	vSerializer	= Jackson2JsonRedisSerializer<Session>(Session::class.java)
		val builder		= RedisSerializationContext.newSerializationContext<String, Session>(kSerializer)
		val context		= builder.value(vSerializer).build()
		return ReactiveRedisTemplate(factory!!, context)
	}
}

fun main(args: Array<String>) {
	runApplication<SessionApplication>(*args)
}