package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.inner.AccountDTO
import feign.*
import reactor.core.publisher.Mono

@Headers("Accept: application/json")
interface AccountServiceAPI {
	@RequestLine("GET /internal/users/find-by-username/{username}")
	fun find(@Param("username") username: String): Mono<AccountDTO?>
}