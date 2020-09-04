package br.com.rcp.commons.dto

import com.fasterxml.jackson.annotation.*
import org.joda.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["id", "created_at", "updated_at"], alphabetic = true)
abstract class AbstractDTO {
	@JsonProperty("id")
	lateinit	var 	identifier	: String

	@JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
	lateinit	var		createdAt	: LocalDateTime

	@JsonProperty(value = "updated_at", access = JsonProperty.Access.READ_ONLY)
	lateinit	var		updatedAt	: LocalDateTime
}