package br.com.services.evaluation.services

import br.com.services.evaluation.domains.City
import br.com.services.evaluation.dto.CityDTO
import br.com.services.evaluation.mapper.CityMapper
import br.com.services.evaluation.mapper.CityMapper.convert
import br.com.services.evaluation.repositories.CityRepository
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.stereotype.Service

@Service
class CityService(repository: CityRepository) : AbstractService<City, CityDTO>(repository, CityMapper) {
	override suspend fun insert(data: CityDTO): CityDTO? {
		return repository.insert(data.let(::convert))?.let(::convert)
	}

	override suspend fun update(identifier: Long, data: CityDTO): CityDTO? {
		val	entity	= repository.find(identifier) ?: throw RuntimeException("Entity not found!")
		val	model	= data.let(::convert)
		entity.copy(model)
		return repository.update(entity)?.let(::convert)
	}
}