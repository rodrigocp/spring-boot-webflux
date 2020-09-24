package br.com.rcp.gateway.dto

import com.fasterxml.jackson.annotation.*
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["token", "ttl", "issued", "data"], alphabetic = false)
data class SessionDTO(
	@JsonProperty("token")
	val	token	: String,

	@JsonProperty("expires")
	var	expires	: Long?,

	@JsonProperty("issued")
	var	issued	: LocalDateTime?,

	@JsonProperty("data")
	val	data	: Map<String, Any>
)