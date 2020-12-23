package br.com.services.evaluation.services

import br.com.services.evaluation.domains.Holding
import br.com.services.evaluation.dto.HoldingDTO
import br.com.services.evaluation.mapper.HoldingMapper
import br.com.services.evaluation.mapper.HoldingMapper.convert
import br.com.services.evaluation.repositories.*
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HoldingService(repository: HoldingRepository) : AbstractService<Holding, HoldingDTO>(repository, HoldingMapper) {
	@Autowired private lateinit var cityRepository		: CityRepository
	@Autowired private lateinit var stateRepository		: StateRepository
	@Autowired private lateinit var countryRepository	: CountryRepository

	override suspend fun insert(data: HoldingDTO): HoldingDTO? {
		val	model	  = data.let(::convert)
		val	city	  = cityRepository.find(data.city ?: 0)		   ?: throw RuntimeException("Entity not found!")
		val	state	  = stateRepository.find(city.state ?: 0)	   ?: throw RuntimeException("Entity not found!")
		val	country	  = countryRepository.find(state.country ?: 0) ?: throw RuntimeException("Entity not found!")
		model.city	  = city.identifier
		model.state	  = state.identifier
		model.country = country.identifier
		return repository.insert(model)?.let(::convert)
	}

	override suspend fun update(identifier: Long, data: HoldingDTO): HoldingDTO? {
		val	entity	  = repository.find(identifier)			   ?: throw RuntimeException("Entity not found!")
		val	city	  = cityRepository.find(data.city ?: 0)		   ?: throw RuntimeException("Entity not found!")
		val	state	  = stateRepository.find(city.state ?: 0)	   ?: throw RuntimeException("Entity not found!")
		val	country	  = countryRepository.find(state.country ?: 0) ?: throw RuntimeException("Entity not found!")
		val	model	  = data.let(::convert)
		model.city	  = city.identifier
		model.state	  = state.identifier
		model.country = country.identifier
		entity.copy(model)
		return repository.update(entity)?.let(::convert)
	}
}