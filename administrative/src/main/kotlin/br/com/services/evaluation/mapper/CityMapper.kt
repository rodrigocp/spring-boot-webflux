package br.com.services.evaluation.mapper

import br.com.services.evaluation.domains.City
import br.com.services.evaluation.dto.CityDTO
import br.com.services.evaluation.mapper.base.Mapper

object CityMapper : Mapper<City, CityDTO> {
	override fun convert(value: City): CityDTO {
		return CityDTO().apply {
			identifier	= value.identifier
			name		= value.name
			state		= value.state
			country		= value.country
		}
	}

	override fun convert(value: CityDTO): City {
		return City(name = value.name, state = value.state)
	}
}