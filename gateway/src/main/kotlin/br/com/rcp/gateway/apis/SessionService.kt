package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.SessionDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.function.ServerResponse

@FeignClient("session-service", url = "http://localhost:8060")
interface SessionService {
	@GetMapping(path = ["/sessions/{token}"])
	fun find(@PathVariable("token") token: String) : SessionDTO

	@PostMapping(path = ["/sessions/"])
	fun create(@RequestBody data: SessionDTO) : ServerResponse

	@DeleteMapping(path = ["/sessions/"])
	fun remove(@PathVariable("token") token: String) : ServerResponse
}