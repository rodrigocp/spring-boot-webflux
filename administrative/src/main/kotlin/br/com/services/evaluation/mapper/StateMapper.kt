package br.com.services.evaluation.mapper

import br.com.services.evaluation.domains.State
import br.com.services.evaluation.dto.StateDTO
import br.com.services.evaluation.mapper.base.Mapper

object StateMapper : Mapper<State, StateDTO> {
	override fun convert(value: State): StateDTO {
		return StateDTO().apply {
			identifier	= value.identifier
			name		= value.name
			country		= value.country
		}
	}

	override fun convert(value: StateDTO): State {
		return State(null, null, null, value.name, value.country)
	}
}