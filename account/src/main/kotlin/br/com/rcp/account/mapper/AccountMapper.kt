package br.com.rcp.account.mapper

import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.AccountDTO
import br.com.rcp.account.utils.Utilities.toLocalDateTime
import br.com.rcp.commons.dto.mapper.Mapper
import java.time.format.DateTimeFormatter

class AccountMapper: Mapper<Account, AccountDTO> {
	override fun toDTO(model: Account?): AccountDTO? {
		return model?.let {
			AccountDTO().apply {
				identifier	= it.identifier.toHexString()
				createdAt	= DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt.toLocalDateTime())
				updatedAt	= DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt.toLocalDateTime())
				username	= it.username
				fullname	= it.fullname
				email		= it.email
				enabled		= it.enabled
				roles		= it.roles
			}
		}
	}

	override fun toDomain(model: AccountDTO?): Account? {
		return model?.let {
			Account().apply {
				username	= it.username	?: ""
				fullname	= it.fullname	?: ""
				email		= it.email		?: ""
				password	= it.password	?: ""
				roles		= it.roles		?: mutableListOf()
			}
		}
	}
}