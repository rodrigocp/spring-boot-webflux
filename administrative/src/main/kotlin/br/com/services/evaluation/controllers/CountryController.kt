package br.com.services.evaluation.controllers

import br.com.services.evaluation.controllers.base.AbstractController
import br.com.services.evaluation.domains.Country
import br.com.services.evaluation.dto.CountryDTO
import br.com.services.evaluation.services.CountryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
class CountryController(service: CountryService): AbstractController<Country, CountryDTO>(service) {
	//
}