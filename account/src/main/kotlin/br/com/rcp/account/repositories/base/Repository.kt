package br.com.rcp.account.repositories.base

import br.com.rcp.account.domains.base.Record
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.query.Query

interface Repository<T: Record> {
	suspend fun find()						: List<T>
	suspend fun find(query: Query)			: List<T>
	suspend fun find(identifier: String)	: T?
	suspend fun save(document: T)			: T?
	suspend fun remove(identifier: String)	: DeleteResult
}