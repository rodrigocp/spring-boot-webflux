package br.com.services.evaluation.mapper

import br.com.services.evaluation.domains.Holding
import br.com.services.evaluation.dto.HoldingDTO
import br.com.services.evaluation.mapper.base.Mapper

object HoldingMapper : Mapper<Holding, HoldingDTO> {
	override fun convert(value: Holding): HoldingDTO {
		return HoldingDTO().apply {
			identifier	= value.identifier
			name		= value.name
			ftin		= value.ftin
			city		= value.city
			state		= value.state
			country		= value.country
		}
	}

	override fun convert(value: HoldingDTO): Holding {
		return Holding(name = value.name, ftin = value.ftin, city = value.city)
	}
}