package br.com.services.evaluation.services

import br.com.services.evaluation.domains.State
import br.com.services.evaluation.dto.StateDTO
import br.com.services.evaluation.mapper.StateMapper
import br.com.services.evaluation.mapper.StateMapper.convert
import br.com.services.evaluation.repositories.StateRepository
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.stereotype.Service

@Service
class StateService(repository: StateRepository) : AbstractService<State, StateDTO>(repository, StateMapper) {
	override suspend fun insert(data: StateDTO): StateDTO? {
		return repository.insert(data.let(::convert))?.let(::convert)
	}

	override suspend fun update(identifier: Long, data: StateDTO): StateDTO? {
		val	entity	= repository.find(identifier) ?: throw RuntimeException("Entity not found!")
		val	model	= data.let(::convert)
		entity.copy(model)
		return repository.update(entity)?.let(::convert)
	}
}