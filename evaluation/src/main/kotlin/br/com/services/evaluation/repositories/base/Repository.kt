package br.com.services.evaluation.repositories.base

import br.com.services.evaluation.constants.Constants.EntityKey.IDENTIFIER
import br.com.services.evaluation.extensions.await
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.relational.core.query.Criteria.where

abstract class Repository<T>(private val client: DatabaseClient) {
	suspend inline fun <reified T> find() : List<T> {
		return getClient().select().from(T::class.java).fetch().await()
	}

	suspend inline fun <reified T> find(identifier: Long): List<T> {
		return getClient().select().from(T::class.java).matching(where(IDENTIFIER).`is`(identifier)).fetch().await()
	}

	suspend inline fun <reified T> insert(model: T) {
		getClient().insert().into(T::class.java).using(model).await()
	}

	suspend inline fun <reified T> update(model: T) {
		getClient().update().table(T::class.java).using(model).await()
	}

	suspend inline fun <reified T> delete(identifier: Long) {
		getClient().delete().from(T::class.java).matching(where(IDENTIFIER).`is`(identifier)).await()
	}

	@PublishedApi
	internal fun getClient() : DatabaseClient {
		return client
	}
}