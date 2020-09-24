package br.com.rcp.session.repositories

import br.com.rcp.session.domain.Session
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class SessionRepository(@Autowired private val template: ReactiveRedisTemplate<String, Session>) {
	private	val	operations : ReactiveValueOperations<String, Session> by lazy { template.opsForValue() }

	suspend fun create(key: String, value: Map<String, Any>, ttl: Long) : Boolean {
		return operations.set(key, Session(key, ttl, LocalDateTime.now(), value)).awaitFirstOrElse { false }
	}

	suspend fun find(key: String) : Session? {
		return operations.get(key).awaitFirstOrNull()
	}

	suspend fun remove(key: String) : Boolean {
		return operations.delete(key).awaitFirstOrElse { false }
	}
}