package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.AccountDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("account-service", url = "http://localhost:8070")
interface AccountClient {
	@GetMapping("/users/{username}")
	fun find(@PathVariable("username") username: String): AccountDTO?

	@GetMapping("/users/", consumes = [APPLICATION_STREAM_JSON_VALUE])
	fun find(): List<AccountDTO>
}