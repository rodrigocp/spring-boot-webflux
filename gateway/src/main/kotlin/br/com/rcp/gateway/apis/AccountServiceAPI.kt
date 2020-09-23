package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.AccountDTO
import br.com.rcp.gateway.dto.LoginDTO
import feign.Headers
import feign.RequestLine
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Headers("Accept: application/json")
interface AccountServiceAPI {
	@RequestLine("GET /users/{username}")
	fun find(@PathVariable("username") username: String): Mono<AccountDTO?>

	@RequestLine("GET /users/")
	fun find(): Flux<AccountDTO>

	@RequestLine("POST /users/validate/")
	@Headers("Content-Type: application/json")
	fun validate(@RequestBody login: LoginDTO): Mono<AccountDTO?>
}