package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.SessionDTO
import feign.*
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

@Headers("Accept: application/json")
interface SessionServiceAPI {
	@RequestLine("GET /sessions?token={token}")
	fun find(@Param("token") token: String) : Mono<SessionDTO?>

	@RequestLine("POST /sessions")
	fun create(@RequestBody session: SessionDTO) : Mono<SessionDTO?>

	@RequestLine("DELETE /sessions?token={token}")
	fun remove(@Param("token") token: String) : Mono<Unit>
}