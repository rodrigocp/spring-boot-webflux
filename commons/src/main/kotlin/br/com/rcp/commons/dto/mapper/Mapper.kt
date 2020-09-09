package br.com.rcp.commons.dto.mapper

import br.com.rcp.commons.domain.Domain
import br.com.rcp.commons.dto.AbstractDTO

interface Mapper<S: Domain, D: AbstractDTO> {
	fun	toDTO	(model: S?): D?
	fun	toDomain(model: D?): S?
}