package br.com.rcp.account.handlers.base

import br.com.rcp.account.domains.base.Record
import br.com.rcp.account.dto.base.DataTransfer
import br.com.rcp.account.mapper.base.Mapper
import br.com.rcp.account.repositories.base.Repository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON
import org.springframework.web.reactive.function.server.*

abstract class AbstractHandler<R : Record, D : DataTransfer> (protected val repository : Repository<R>, protected val mapper : Mapper<R, D>) : Handler {
	override suspend fun find(request: ServerRequest): ServerResponse {
		val	identifier	= request.pathVariable("identifier")
		val	record		= repository.find(identifier)
		val	response	= mapper.toDTO(record)

		return if (response != null) {
			ServerResponse.ok().contentType(APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}

	override suspend fun fetch(request: ServerRequest): ServerResponse {
		return ServerResponse.ok().contentType(APPLICATION_STREAM_JSON).bodyValueAndAwait(repository.find().map { mapper.toDTO(it) })
	}

	override suspend fun remove(request: ServerRequest): ServerResponse {
		repository.remove(request.pathVariable("identifier"))
		return ServerResponse.ok().buildAndAwait()
	}
}