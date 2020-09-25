package br.com.rcp.session.repositories

import br.com.rcp.session.domain.Session
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.stereotype.Repository

@Repository
class SessionRepository(@Autowired private val template: ReactiveRedisTemplate<String, Session>) {
	private	val	operations : ReactiveValueOperations<String, Session> by lazy { template.opsForValue() }

	suspend fun create(key: String, issued: String, ttl: Long, value: Map<String, Any>) : Boolean {
		return operations.set(key, Session(key, ttl, issued, value)).awaitFirstOrElse { false }
	}

	suspend fun find(key: String) : Session? {
		return operations.get(key).awaitFirstOrNull()
	}

	suspend fun remove(key: String) : Boolean {
		return operations.delete(key).awaitFirstOrElse { false }
	}
}