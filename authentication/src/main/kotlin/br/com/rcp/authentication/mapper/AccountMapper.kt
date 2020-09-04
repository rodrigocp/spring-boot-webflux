package br.com.rcp.authentication.mapper

import br.com.rcp.authentication.domains.Account
import br.com.rcp.authentication.dto.AccountDTO
import br.com.rcp.commons.dto.mapper.Mapper

class AccountMapper: Mapper<Account, AccountDTO> {
	override fun toDTO(model: Account): AccountDTO {
		return AccountDTO().apply {
			username	= model.username
			fullname	= model.fullname
			email		= model.email
		}
	}

	override fun toDomain(model: AccountDTO): Account {
		return Account().apply {
			username	= model.username	?: ""
			fullname	= model.fullname	?: ""
			email		= model.email		?: ""
			password	= model.password	?: ""
		}
	}
}