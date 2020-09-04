package br.com.rcp.commons.repository

import br.com.rcp.commons.domain.Domain
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.query.Query

interface Repository<T: Domain> {
	suspend fun find()						: List<T>
	suspend fun find(query: Query)			: List<T>
	suspend fun find(identifier: String)	: T?
	suspend fun save(document: T)			: T?
	suspend fun remove(identifier: String)	: DeleteResult?
}