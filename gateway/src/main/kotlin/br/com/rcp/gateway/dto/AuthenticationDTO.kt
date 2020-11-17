package br.com.rcp.gateway.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
data class AuthenticationDTO(
	@JsonProperty("identifier")
	var identifier	: String,

	@JsonProperty("username")
	var username	: String,

	@JsonProperty("password")
	var password	: String,

	@JsonProperty("roles")
	var	roles		: MutableList<String>
)