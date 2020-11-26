package br.com.services.evaluation.services.base

import br.com.services.evaluation.domains.base.Domain
import br.com.services.evaluation.dto.base.DataTransfer

interface Service<T:Domain, DTO: DataTransfer> {
	suspend fun find()					 : List<DTO>
	suspend fun find(identifier: Long)	 : DTO?
	suspend fun insert(model: T) 		 : DTO?
	suspend fun update(model: T)		 : Int
	suspend fun delete(identifier: Long) : Int
}