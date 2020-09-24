package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.SessionDTO
import feign.Headers
import feign.RequestLine
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@Headers("Accept: application/json")
interface SessionServiceAPI {
	@RequestLine("GET /sessions/{token}")
	fun find(@PathVariable("token") token: String) : Mono<SessionDTO?>

	@RequestLine("POST /sessions")
	fun create(@RequestBody session: SessionDTO) : Mono<SessionDTO?>

	@RequestLine("DELETE /sessions/{token}")
	fun remove(@PathVariable("token") token: String) : Mono<Unit>
}