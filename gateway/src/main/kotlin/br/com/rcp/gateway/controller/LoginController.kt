package br.com.rcp.gateway.controller

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.dto.LoginDTO
import br.com.rcp.gateway.dto.SessionDTO
import br.com.rcp.gateway.utils.RandomString
import br.com.rcp.gateway.utils.Utilities.writeJSON
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController(@Autowired private val service: AccountServiceAPI) {
	@PostMapping("/")
	suspend fun login(@RequestBody login: LoginDTO) : ResponseEntity<SessionDTO> {
		val	account	= service.validate(login).awaitSingle()
		val	mapper	= ObjectMapper()
		val	random	= RandomString.generate()
		val	token	= SessionDTO(random, mapper.writeJSON(account), 600)
		return ResponseEntity.ok(token)
	}
}