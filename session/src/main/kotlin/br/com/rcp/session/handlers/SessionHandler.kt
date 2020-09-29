package br.com.rcp.session.handlers

import br.com.rcp.session.dto.SessionDTO
import br.com.rcp.session.mappers.SessionMapper
import br.com.rcp.session.repositories.SessionRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class SessionHandler(private val repository: SessionRepository) {
	private	val	mapper	: SessionMapper	get()	= SessionMapper()

	suspend fun persist(request: ServerRequest): ServerResponse {
		val	data		= request.awaitBodyOrNull<SessionDTO>()
		val	document	= data?.let { repository.create(it.token, DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()), it.expires, it.data) } ?: false

		return if (document && data != null) {
			ServerResponse.ok().bodyValueAndAwait(data)
		} else {
			ServerResponse.badRequest().buildAndAwait()
		}
	}

	suspend fun find(request: ServerRequest): ServerResponse {
		val	key			= request.queryParam("token").orElse("")
		val	document	= repository.find(key)
		val	response	= document?.let { mapper.toDTO(it) }

		return if (response != null) {
			ServerResponse.ok().bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}

	suspend fun remove(request: ServerRequest): ServerResponse {
		val	key			= request.queryParam("token").orElse("")
		val	document	= repository.remove(key)

		return if (document) {
			ServerResponse.ok().buildAndAwait()
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}
}