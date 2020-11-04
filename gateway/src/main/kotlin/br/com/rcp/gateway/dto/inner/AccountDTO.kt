package br.com.rcp.gateway.dto.inner

import br.com.rcp.gateway.dto.base.DataTransfer
import com.fasterxml.jackson.annotation.*
import org.hibernate.validator.constraints.Length

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
data class AccountDTO(
	@JsonProperty("identifier")
	override var identifier	: String,

	@JsonProperty("created_at")
	override var createdAt	: String,

	@JsonProperty("updated_at")
	override var updatedAt	: String,

	@JsonProperty("username")
	var username	: String,

	@JsonProperty("name")
	var	name		: String,

	@JsonProperty("email")
	var	email		: String,

	@JsonProperty("password")
	@Length(min = 8)
	var password	: String,

	@JsonProperty("enabled")
	var	enabled		: Boolean,

	@JsonProperty("roles")
	var	roles		: MutableList<String>
) : DataTransfer