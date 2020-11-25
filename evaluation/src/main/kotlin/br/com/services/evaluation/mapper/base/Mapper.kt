package br.com.services.evaluation.mapper.base

import br.com.services.evaluation.domains.base.Domain

interface Mapper<T : Domain, DTO> {
	fun convert(value: T)	: DTO
	fun convert(value: DTO)	: T
}
