package br.com.rcp.commons.repository

import br.com.rcp.commons.domain.Domain
import com.mongodb.client.result.DeleteResult
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.*
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class AbstractRepository<T: Domain>(protected val template: ReactiveMongoTemplate) : Repository<T> {
	override suspend fun find(): List<T> {
		return template.findAll(domain).collectList().awaitFirst()
	}

	override suspend fun find(query: Query): List<T> {
		return template.find(query, domain).collectList().awaitFirst()
	}

	override suspend fun find(identifier: String): T? {
		return template.findById(identifier, domain).awaitFirstOrNull()
	}

	override suspend fun save(document: T): T? {
		return template.save(document).awaitFirstOrNull()
	}

	override suspend fun remove(identifier: String): DeleteResult? {
		return template.remove(Query(Criteria.where("identifier").isEqualTo(identifier)), domain).awaitFirstOrNull()
	}

	private val domain: Class<T> get() {
		return (this.javaClass::getGenericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
	}
}