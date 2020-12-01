package br.com.services.evaluation.services.base

import br.com.services.evaluation.domains.base.Domain
import br.com.services.evaluation.dto.base.DataTransfer

interface Service<T:Domain, DTO: DataTransfer> {
	suspend fun find()					 			 : List<DTO>
	suspend fun find(identifier: Long)	 			 : DTO?
	suspend fun insert(data: DTO)		 			 : DTO?
	suspend fun update(identifier: Long, data: DTO)  : DTO?
	suspend fun remove(identifier: Long) 			 : Int
}