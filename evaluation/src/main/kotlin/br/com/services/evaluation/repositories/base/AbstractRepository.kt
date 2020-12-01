package br.com.services.evaluation.repositories.base

import br.com.services.evaluation.constants.Constants.EntityKey.IDENTIFIER
import br.com.services.evaluation.domains.base.Domain
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.empty
import org.springframework.data.relational.core.query.Query.query
import java.lang.reflect.ParameterizedType
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate as Template

@Suppress("UNCHECKED_CAST")
abstract class AbstractRepository<T : Domain>(protected val template: Template): Repository<T> {
	private	val	domain : Class<T> get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>

	override suspend fun find() : List<T> {
		return template.select(empty(), domain).collectList().awaitFirst()
	}

	override suspend fun find(identifier: Long): T? {
		return template.selectOne(query(where(IDENTIFIER).`is`(identifier)), domain).awaitFirstOrNull()
	}

	override suspend fun insert(model: T) : T? {
		return template.insert(model).awaitFirstOrNull()
	}

	override suspend fun update(model: T) : T? {
		return template.update(model).awaitFirstOrNull()
	}

	override suspend fun delete(identifier: Long) : Int {
		return template.delete(query(where(IDENTIFIER).`is`(identifier)), domain).awaitFirst()
	}
}