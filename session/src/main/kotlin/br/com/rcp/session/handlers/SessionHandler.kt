package br.com.rcp.session.handlers

import br.com.rcp.session.dto.SessionDTO
import br.com.rcp.session.mappers.SessionMapper
import br.com.rcp.session.repositories.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Service
class SessionHandler(@Autowired private val repository: SessionRepository) {
	private	val	mapper	: SessionMapper	get()	= SessionMapper()

	suspend fun persist(request: ServerRequest): ServerResponse {
		val	data		= request.awaitBodyOrNull<SessionDTO>()
		val	document	= data?.let { repository.create(it.token, it.data, it.expires) } ?: false

		return if (document) {
			ServerResponse.created(URI.create("")).buildAndAwait()
		} else {
			ServerResponse.badRequest().buildAndAwait()
		}
	}

	suspend fun find(request: ServerRequest): ServerResponse {
		val	key			= request.pathVariable("token")
		val	document	= repository.find(key)
		val	response	= document?.let { mapper.toDTO(it) }

		return if (response != null) {
			ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}

	suspend fun remove(request: ServerRequest): ServerResponse {
		val	key			= request.pathVariable("token")
		val	document	= repository.remove(key)

		return if (document) {
			ServerResponse.ok().buildAndAwait()
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}
}