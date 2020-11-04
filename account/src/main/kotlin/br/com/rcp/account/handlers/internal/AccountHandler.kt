package br.com.rcp.account.handlers.internal

import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.internal.AccountDTO
import br.com.rcp.account.handlers.base.AbstractHandler
import br.com.rcp.account.mapper.internal.AccountMapper
import br.com.rcp.account.repositories.AccountRepository
import org.springframework.data.mongodb.core.query.*
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service("internalAccountHandler")
class AccountHandler(repository: AccountRepository) : AbstractHandler<Account, AccountDTO>(repository, AccountMapper) {
	override suspend fun persist(request: ServerRequest): ServerResponse {
		TODO("Not yet implemented")
	}

	override suspend fun update(request: ServerRequest): ServerResponse {
		TODO("Not yet implemented")
	}

	suspend fun findByUsername(request: ServerRequest): ServerResponse {
		val	username	= request.pathVariable("username")
		val	query		= Query(Criteria.where("username").isEqualTo(username))
		val	document	= repository.find(query).stream().findFirst().orElse(null)
		val	response	= document?.let { mapper.toDTO(it) }

		return if (response != null) {
			ServerResponse.ok().contentType(APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.notFound().buildAndAwait()
		}
	}
}