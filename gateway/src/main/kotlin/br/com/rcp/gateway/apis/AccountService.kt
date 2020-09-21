package br.com.rcp.gateway.apis

import br.com.rcp.gateway.dto.AccountDTO
import br.com.rcp.gateway.dto.LoginDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient("account-service", url = "http://localhost:8070")
interface AccountService {
	@GetMapping(path = ["/users/{username}"])
	fun find(@PathVariable("username") username: String): AccountDTO?

	@GetMapping(path = ["/users/"])
	fun find(): List<AccountDTO>

	@PostMapping(path = ["/users/"])
	fun validate(@RequestBody login: LoginDTO): AccountDTO?
}