package br.com.services.evaluation.repositories.base

import br.com.services.evaluation.domains.base.Domain

interface Repository<T: Domain> {
	suspend	fun	find()						: List<T>
	suspend	fun	find(identifier: Long)		: T?
	suspend	fun	insert(data: T)				: T?
	suspend	fun	update(data: T)				: Int
	suspend	fun	delete(identifier: Long)	: Int
}
