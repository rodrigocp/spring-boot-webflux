package br.com.rcp.gateway.dto

import br.com.rcp.commons.dto.AbstractDTO
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

class AccountDTO: AbstractDTO() {
	@JsonProperty("username")
	var 	username	: String?				= null

	@JsonProperty("name")
	var 	fullname	: String?				= null

	@JsonProperty("email")
	var 	email		: String?				= null

	@JsonProperty("password")
	@Length(min = 8)
	var 	password	: String?				= null

	@JsonProperty("password")
	var 	roles		: MutableList<String>?	= null
}