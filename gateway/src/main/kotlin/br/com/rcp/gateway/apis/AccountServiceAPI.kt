package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.AccountDTO
import br.com.rcp.gateway.dto.LoginDTO
import feign.Headers
import feign.RequestLine
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@Headers("Accept: application/json")
interface AccountServiceAPI {
	@RequestLine("GET /users/{username}")
	fun find(@PathVariable("username") username: String): Mono<AccountDTO?>

	@RequestLine("POST /users/validate/")
	fun validate(@RequestBody login: LoginDTO): Mono<AccountDTO?>
}