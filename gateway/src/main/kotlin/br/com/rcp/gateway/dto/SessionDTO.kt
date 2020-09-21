package br.com.rcp.gateway.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["token", "ttl", "data"], alphabetic = false)
data class SessionDTO(
	@JsonProperty("token")
	val	token	: String,

	@JsonProperty("data")
	val	data	: String,

	@JsonProperty("ttl")
	val	expires	: Long
)