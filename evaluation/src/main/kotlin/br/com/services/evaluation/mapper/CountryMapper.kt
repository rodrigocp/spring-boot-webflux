package br.com.services.evaluation.mapper

import br.com.services.evaluation.domains.Country
import br.com.services.evaluation.dto.CountryDTO
import br.com.services.evaluation.mapper.base.Mapper

object CountryMapper : Mapper<Country, CountryDTO> {
	override fun convert(value: Country): CountryDTO {
		TODO("Not yet implemented")
	}

	override fun convert(value: CountryDTO): Country {
		TODO("Not yet implemented")
	}
}
