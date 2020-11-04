package br.com.rcp.account.mapper.internal
import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.internal.AccountDTO
import br.com.rcp.account.mapper.base.Mapper
import br.com.rcp.account.utils.Utilities.toLocalDateTime
import java.time.format.DateTimeFormatter

object AccountMapper : Mapper<Account, AccountDTO> {
	override fun toDTO(data: Account?): AccountDTO? {
		return data?.let {
			AccountDTO(
				identifier	= it.identifier.toHexString(),
				createdAt	= DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt.toLocalDateTime()),
				updatedAt	= DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt.toLocalDateTime()),
				username	= it.username,
				name		= it.name,
				email		= it.email,
				enabled		= it.enabled,
				password	= it.password,
				roles		= it.roles
			)
		}
	}

	override fun toRecord(data: AccountDTO?): Account? {
		return data?.let {
			return Account(
				username	= it.username 	?: "",
				password 	= it.password	?: "",
				name		= it.name		?: "",
				email 		= it.email 		?: "",
				enabled		= true,
				roles		= it.roles
			)
		}
	}
}