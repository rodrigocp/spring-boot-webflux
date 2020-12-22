package br.com.services.evaluation.services

import br.com.services.evaluation.domains.Country
import br.com.services.evaluation.dto.CountryDTO
import br.com.services.evaluation.mapper.CountryMapper
import br.com.services.evaluation.mapper.CountryMapper.convert
import br.com.services.evaluation.repositories.CountryRepository
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.stereotype.Service

@Service
class CountryService(repository: CountryRepository) : AbstractService<Country, CountryDTO>(repository, CountryMapper) {
	override suspend fun insert(data: CountryDTO): CountryDTO? {
		return repository.insert(data.let(::convert))?.let(::convert)
	}

	override suspend fun update(identifier: Long, data: CountryDTO): CountryDTO? {
		val	entity	= repository.find(identifier) ?: throw RuntimeException("Entity not found!")
		val	model	= data.let(::convert)
		entity.copy(model)
		return repository.update(entity)?.let(::convert)
	}
}