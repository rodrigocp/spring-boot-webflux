package br.com.rcp.gateway.controller

import br.com.rcp.gateway.apis.AccountService
import br.com.rcp.gateway.apis.SessionService
import br.com.rcp.gateway.dto.LoginDTO
import br.com.rcp.gateway.dto.SessionDTO
import br.com.rcp.gateway.utils.RandomString
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController(@Autowired private val service: AccountService, @Autowired private val session: SessionService) {
	@PostMapping("/")
	fun login(login: LoginDTO) {
		val	account	= service.validate(login)
		val	mapper	= ObjectMapper()
		val	random	= RandomString.generate()
		val	token	= SessionDTO(random, mapper.writeValueAsString(account), 600)

		if (session.create(token).statusCode().is2xxSuccessful) {
			TODO("Implement")
		}
	}
}