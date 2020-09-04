package br.com.rcp.authentication.dto

import br.com.rcp.commons.dto.AbstractDTO
import com.fasterxml.jackson.annotation.JsonProperty

class AccountDTO: AbstractDTO() {
	@JsonProperty("username")
	var 	username	: String?	= null

	@JsonProperty("name")
	var 	fullname	: String?	= null

	@JsonProperty("email")
	var 	email		: String?	= null

	@JsonProperty("password")
	var 	password	: String?	= null
}