package br.com.services.evaluation.services

import br.com.services.evaluation.domains.City
import br.com.services.evaluation.dto.CityDTO
import br.com.services.evaluation.mapper.CityMapper
import br.com.services.evaluation.mapper.CityMapper.convert
import br.com.services.evaluation.repositories.*
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CityService(repository: CityRepository) : AbstractService<City, CityDTO>(repository, CityMapper) {
	@Autowired private lateinit var stateRepository		: StateRepository
	@Autowired private lateinit var countryRepository	: CountryRepository

	override suspend fun insert(data: CityDTO): CityDTO? {
		val	model		= data.let(::convert)
		val	state		= stateRepository.find(data.state ?: 0) 		?: throw RuntimeException("Entity not found!")
		val	country		= countryRepository.find(state.country ?: 0) 	?: throw RuntimeException("Entity not found!")
		model.country	= country.identifier
		model.state		= state.identifier
		return repository.insert(model)?.let(::convert)
	}

	override suspend fun update(identifier: Long, data: CityDTO): CityDTO? {
		val	entity		= repository.find(identifier)			?: throw RuntimeException("Entity not found!")
		val	state		= stateRepository.find(data.state ?: 0)	?: throw RuntimeException("Entity not found!")
		val	country		= countryRepository.find(state.country	?: 0) ?: throw RuntimeException("Entity not found!")
		val	model		= data.let(::convert)
		model.country	= country.identifier
		model.state		= state.identifier
		entity.copy(model)
		return repository.update(entity)?.let(::convert)
	}
}