package br.com.services.evaluation.services

import br.com.services.evaluation.domains.Country
import br.com.services.evaluation.dto.CountryDTO
import br.com.services.evaluation.mapper.CountryMapper
import br.com.services.evaluation.repositories.CountryRepository
import br.com.services.evaluation.services.base.AbstractService
import org.springframework.stereotype.Service

@Service
class CountryService(repository: CountryRepository) : AbstractService<Country, CountryDTO>(repository, CountryMapper) {
	override suspend fun insert(model: Country): CountryDTO? {
		TODO("Not yet implemented")
	}

	override suspend fun update(model: Country): Int {
		TODO("Not yet implemented")
	}
}