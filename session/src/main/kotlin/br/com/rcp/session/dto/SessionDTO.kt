package br.com.rcp.session.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["token", "issued", "ttl", "data"], alphabetic = false)
data class SessionDTO(
	@JsonProperty("token")
	val	token	: String,

	@JsonProperty("expires")
	val	expires	: Long,

	@JsonProperty("issued")
	var	issued	: String?,

	@JsonProperty("data")
	val	data	: Map<String, Any>
)