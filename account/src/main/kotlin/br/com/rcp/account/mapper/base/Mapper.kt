package br.com.rcp.account.mapper.base

import br.com.rcp.account.domains.base.Record

interface Mapper<R : Record, D> {
	fun toDTO	(data : R?)	: D?
	fun toRecord(data : D?)	: R?
}