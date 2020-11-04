package br.com.rcp.account.mapper.base

import br.com.rcp.account.domains.base.Record
import br.com.rcp.account.dto.base.DataTransfer

interface Mapper<R : Record, D : DataTransfer> {
	fun toDTO	(data : R?)	: D?
	fun toRecord(data : D?)	: R?
}