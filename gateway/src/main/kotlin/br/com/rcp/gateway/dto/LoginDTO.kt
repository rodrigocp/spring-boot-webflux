package br.com.rcp.gateway.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["username", "password"], alphabetic = false)
class LoginDTO(
	@JsonProperty("username")
	var	username	: String?,

	@JsonProperty("password")
	var	password	: String?
)