package br.com.rcp.account.handlers

import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.AuthenticationDTO
import br.com.rcp.account.handlers.base.AbstractHandler
import br.com.rcp.account.mapper.AuthenticationMapper
import br.com.rcp.account.repositories.AccountRepository
import org.springframework.data.mongodb.core.query.*
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class AuthenticationHandler(repository: AccountRepository) : AbstractHandler<Account, AuthenticationDTO>(repository, AuthenticationMapper) {
	override suspend fun find(request: ServerRequest): ServerResponse {
		throw UnsupportedOperationException("Not supported!")
	}

	override suspend fun fetch(request: ServerRequest): ServerResponse {
		throw UnsupportedOperationException("Not supported!")
	}

	override suspend fun remove(request: ServerRequest): ServerResponse {
		throw UnsupportedOperationException("Not supported!")
	}

	override suspend fun persist(request: ServerRequest): ServerResponse {
		throw UnsupportedOperationException("Not supported!")
	}

	override suspend fun update(request: ServerRequest): ServerResponse {
		throw UnsupportedOperationException("Not supported!")
	}

	suspend fun findByUsername(request: ServerRequest): ServerResponse {
		val	username	= request.pathVariable("username")
		val	query		= Query(Criteria.where("username").isEqualTo(username).and("enabled").isEqualTo(true))
		val	document	= repository.find(query).stream().findFirst().orElse(null)
		val	response	= document?.let { mapper.toDTO(it) }

		return if (response != null) {
			ServerResponse.ok().contentType(APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}
}