package br.com.services.evaluation.services.base

import br.com.services.evaluation.domains.base.Domain
import br.com.services.evaluation.dto.base.DataTransfer
import br.com.services.evaluation.mapper.base.Mapper
import br.com.services.evaluation.repositories.base.Repository

abstract class AbstractService<T : Domain, DTO : DataTransfer>(protected val repository : Repository<T>, protected val mapper : Mapper<T, DTO>) : Service<T, DTO> {
	override suspend fun find(): List<DTO> {
		return repository.find().map(mapper::convert)
	}

	override suspend fun find(identifier: Long): DTO? {
		return repository.find(identifier)?.let(mapper::convert)
	}

	override suspend fun delete(identifier: Long): Int {
		return repository.delete(identifier)
	}
}