package br.com.services.evaluation.controllers

import br.com.services.evaluation.services.CountryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegionController(private val service: CountryService) {
	@GetMapping("/")
	suspend fun find() : ResponseEntity<Any> {
		return ResponseEntity.ok(service.find())
	}
}