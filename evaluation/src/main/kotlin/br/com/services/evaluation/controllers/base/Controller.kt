package br.com.services.evaluation.controllers.base

interface Controller<DTO> {
	suspend fun find()					 			: List<DTO>
	suspend fun find(identifier: Long)	 			: DTO?
	suspend fun insert(data: DTO)					: DTO?
	suspend fun update(identifier: Long, data: DTO)	: DTO?
	suspend fun remove(identifier: Long)			: Int
}