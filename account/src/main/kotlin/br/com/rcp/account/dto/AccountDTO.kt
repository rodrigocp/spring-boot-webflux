package br.com.rcp.account.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
data class AccountDTO(
	@JsonProperty("identifier")
	var identifier	: String,

	@JsonProperty("created_at")
	var createdAt	: String,

	@JsonProperty("updated_at")
	var updatedAt	: String,

	@JsonProperty("username")
	var username	: String?,

	@JsonProperty("name")
	var	name		: String?,

	@JsonProperty("email")
	var	email		: String?,

	@JsonProperty("password", access = JsonProperty.Access.WRITE_ONLY)
	var password	: String?,

	@JsonProperty("enabled")
	var	enabled		: Boolean,

	@JsonProperty("roles")
	var	roles		: MutableList<String>
)