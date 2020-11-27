package br.com.services.evaluation.controllers

import br.com.services.evaluation.dto.CountryDTO
import br.com.services.evaluation.services.CountryService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
class RegionController(private val service: CountryService) {
	@GetMapping("/")
	suspend fun find(): ResponseEntity<Any> {
		return ok(service.find())
	}

	@PostMapping("/")
	suspend fun insert(@RequestBody data: CountryDTO): ResponseEntity<CountryDTO?> {
		return service.insert(data).let(::ok)
	}

	@PatchMapping("/{identifier}")
	suspend fun update(@PathVariable("identifier") identifier: Long, @RequestBody data: CountryDTO): ResponseEntity<Int> {
		return service.update(identifier, data).let(::ok)
	}
}