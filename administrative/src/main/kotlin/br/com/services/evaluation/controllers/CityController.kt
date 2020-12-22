package br.com.services.evaluation.controllers

import br.com.services.evaluation.controllers.base.AbstractController
import br.com.services.evaluation.domains.City
import br.com.services.evaluation.dto.CityDTO
import br.com.services.evaluation.services.CityService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cities")
class CityController(service: CityService): AbstractController<City, CityDTO>(service) {
	//
}