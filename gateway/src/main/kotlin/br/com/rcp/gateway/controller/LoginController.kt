package br.com.rcp.gateway.controller

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.apis.SessionServiceAPI
import br.com.rcp.gateway.dto.LoginDTO
import br.com.rcp.gateway.dto.SessionDTO
import br.com.rcp.gateway.utils.RandomString
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/login")
class LoginController(private val service: AccountServiceAPI, private val session: SessionServiceAPI) {
	@PostMapping("/")
	suspend fun login(@RequestBody login: LoginDTO) : ResponseEntity<SessionDTO> {
		val	account		= service.validate(login).awaitSingle()
		val	mapper		= ObjectMapper()
		val	random		= RandomString.generate()
		val	token		= session.create(SessionDTO(random, 600, null, mapper.convertValue(account, object: TypeReference<Map<String, Any>>() {}))).awaitSingle()
		
		return if (token != null) {
			ResponseEntity.status(HttpStatus.CREATED).body(token)
		} else {
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
		}
	}
}