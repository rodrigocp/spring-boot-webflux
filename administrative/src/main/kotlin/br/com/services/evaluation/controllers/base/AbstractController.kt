package br.com.services.evaluation.controllers.base

import br.com.services.evaluation.domains.base.Domain
import br.com.services.evaluation.dto.base.DataTransfer
import br.com.services.evaluation.services.base.Service
import org.springframework.web.bind.annotation.*

abstract class AbstractController<T: Domain, DTO: DataTransfer>(protected val service: Service<T, DTO>) : Controller<DTO> {
	@GetMapping("/")
	override suspend fun find(): List<DTO> {
		return service.find()
	}

	@GetMapping("/{identifier}")
	override suspend fun find(@PathVariable identifier: Long): DTO? {
		return service.find(identifier)
	}

	@PostMapping("/")
	override suspend fun insert(@RequestBody data: DTO): DTO? {
		return service.insert(data)
	}

	@PatchMapping("/{identifier}")
	override suspend fun update(@PathVariable identifier: Long, @RequestBody data: DTO): DTO? {
		return service.update(identifier, data)
	}

	@DeleteMapping("/{identifier}")
	override suspend fun remove(@PathVariable identifier: Long): Int {
		return service.remove(identifier)
	}
}