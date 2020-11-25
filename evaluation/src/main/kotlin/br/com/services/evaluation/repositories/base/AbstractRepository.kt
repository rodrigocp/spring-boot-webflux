package br.com.services.evaluation.repositories.base

import br.com.services.evaluation.constants.Constants.EntityKey.IDENTIFIER
import br.com.services.evaluation.domains.base.Domain
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.relational.core.query.Criteria.where
import java.lang.reflect.ParameterizedType
import java.util.stream.Collectors.toList


@Suppress("UNCHECKED_CAST")
abstract class AbstractRepository<T: Domain>(protected val client: DatabaseClient) : Repository<T> {
	private	val	type	: ParameterizedType	get() = javaClass.genericSuperclass as ParameterizedType
	private	val	domain	: Class<T>			get() = type.actualTypeArguments[0] as Class<T>


	override suspend fun find(): List<T> {
		return client.select().from(domain).fetch().all().collect(toList()).awaitFirst()
	}

	override suspend fun find(identifier: Long): T? {
		return client.select().from(domain).matching(where(IDENTIFIER).`is`(identifier)).fetch().awaitOneOrNull()
	}

	override suspend fun insert(data: T) : T? {
		return client.insert().into(domain).using(data).fetch().first().map(::convert).awaitFirstOrNull()
	}

	override suspend fun update(data: T) : Int {
		return client.update().table(domain).using(data).fetch().awaitRowsUpdated()
	}

	override suspend fun delete(identifier: Long) : Int {
		return client.delete().from(domain).matching(where(IDENTIFIER).`is`(identifier)).fetch().awaitRowsUpdated()
	}

	private fun convert(value: Map<String, Any>) : T {
		return ObjectMapper().convertValue(value, domain)
	}
}
