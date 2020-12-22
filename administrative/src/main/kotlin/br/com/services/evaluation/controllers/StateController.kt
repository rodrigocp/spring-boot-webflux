package br.com.services.evaluation.controllers

import br.com.services.evaluation.controllers.base.AbstractController
import br.com.services.evaluation.domains.State
import br.com.services.evaluation.dto.StateDTO
import br.com.services.evaluation.services.StateService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/states")
class StateController(service: StateService): AbstractController<State, StateDTO>(service) {
	//
}