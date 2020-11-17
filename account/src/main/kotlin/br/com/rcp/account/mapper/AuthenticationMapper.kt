package br.com.rcp.account.mapper
import br.com.rcp.account.domains.Account
import br.com.rcp.account.dto.AuthenticationDTO
import br.com.rcp.account.mapper.base.Mapper

object AuthenticationMapper : Mapper<Account, AuthenticationDTO> {
	override fun toDTO(data: Account?): AuthenticationDTO? {
		return data?.let {
			AuthenticationDTO(
				identifier	= it.identifier.toHexString(),
				username	= it.username,
				password 	= it.password,
				roles		= it.roles
			)
		}
	}

	override fun toRecord(data: AuthenticationDTO?): Account? {
		throw UnsupportedOperationException("Not supported!")
	}
}