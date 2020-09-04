package br.com.rcp.commons.handler

import br.com.rcp.commons.domain.Domain
import br.com.rcp.commons.dto.AbstractDTO
import br.com.rcp.commons.dto.mapper.Mapper
import br.com.rcp.commons.repository.Repository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON
import org.springframework.web.reactive.function.server.*

abstract class Handler<S: Domain, D: AbstractDTO>(protected val repository: Repository<S>, private val mapper: Mapper<S, D>) {
	suspend fun find(request: ServerRequest): ServerResponse {
		val	identifier	= request.pathVariable("identifier")
		val	document	= repository.find(identifier)
		val	response	= document?.let { mapper.toDTO(it) }

		return if (response != null) {
			ServerResponse.ok().contentType(APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}

	suspend fun fetch(request: ServerRequest): ServerResponse {
		return ServerResponse.ok().contentType(APPLICATION_STREAM_JSON).bodyValueAndAwait(repository.find())
	}
}