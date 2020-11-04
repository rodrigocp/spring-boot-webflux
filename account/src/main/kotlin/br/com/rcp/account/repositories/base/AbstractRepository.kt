package br.com.rcp.account.repositories.base

import br.com.rcp.account.domains.base.Record
import com.mongodb.client.result.DeleteResult
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.*

abstract class AbstractRepository<T: Record>(protected val template: ReactiveMongoTemplate, private val domain: Class<T>) : Repository<T> {
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

	override suspend fun remove(identifier: String): DeleteResult {
		return template.remove(Query(Criteria.where("identifier").isEqualTo(identifier)), domain).awaitFirst()
	}
}