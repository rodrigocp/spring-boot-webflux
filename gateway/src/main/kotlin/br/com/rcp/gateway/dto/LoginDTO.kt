package br.com.rcp.gateway.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["username", "password"], alphabetic = false)
data class LoginDTO(
	@JsonProperty("username")
	val	username	: String,

	@JsonProperty("password")
	val	password	: String
)