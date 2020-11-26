package br.com.services.evaluation.repositories.base

import br.com.services.evaluation.constants.Constants.EntityKey.IDENTIFIER
import br.com.services.evaluation.domains.base.Domain
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.relational.core.query.Criteria.where
import java.lang.reflect.ParameterizedType
import java.util.stream.Collectors

@Suppress("UNCHECKED_CAST")
abstract class AbstractRepository<T : Domain>(protected val client: DatabaseClient): Repository<T> {
	private	val	domain : Class<T> get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>

	override suspend fun find() : List<T> {
		return client.select().from(domain).fetch().all().collect(Collectors.toList()).awaitFirst()
	}

	override suspend fun find(identifier: Long): T? {
		return client.select().from(domain).matching(where(IDENTIFIER).`is`(identifier)).fetch().first().awaitFirstOrNull()
	}

	override suspend fun insert(model: T) : T? {
		return client.insert().into(domain).using(model).fetch().first().map { ObjectMapper().convertValue(it, domain) }.awaitFirstOrNull()
	}

	override suspend fun update(model: T) : Int {
		return client.update().table(domain).using(model).fetch().awaitRowsUpdated()
	}

	override suspend fun delete(identifier: Long) : Int {
		return client.delete().from(domain).matching(where(IDENTIFIER).`is`(identifier)).fetch().awaitRowsUpdated()
	}
}