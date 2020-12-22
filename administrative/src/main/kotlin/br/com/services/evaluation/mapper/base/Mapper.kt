package br.com.services.evaluation.mapper.base

import br.com.services.evaluation.domains.base.Domain
import br.com.services.evaluation.dto.base.DataTransfer

interface Mapper<T: Domain, DTO: DataTransfer> {
	fun convert(value: T)	: DTO
	fun convert(value: DTO)	: T
}