package br.com.rcp.account.handlers

import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.AccountDTO
import br.com.rcp.account.mapper.AccountMapper
import br.com.rcp.account.repositories.AccountRepository
import br.com.rcp.commons.handler.AbstractHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.*
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class AccountHandler(@Autowired repository: AccountRepository, @Autowired private val encoder: PasswordEncoder): AbstractHandler<Account, AccountDTO>(repository, AccountMapper()) {
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

	override suspend fun persist(request: ServerRequest): ServerResponse {
		val	data		= request.awaitBodyOrNull<AccountDTO>()
		val	persist		= mapper.toDomain(data)?.apply { encoder.encode(password) }
		val	document	= persist?.let { repository.save(persist) }
		val response	= mapper.toDTO(document)

		return if (response != null) {
			ServerResponse.ok().contentType(APPLICATION_JSON).bodyValueAndAwait(response)
		} else {
			ServerResponse.badRequest().buildAndAwait()
		}
	}

	override suspend fun update(request: ServerRequest): ServerResponse {
		TODO("Not yet implemented")
	}
}