package br.com.rcp.commons.dto

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = ["id", "created_at", "updated_at"], alphabetic = true)
abstract class AbstractDTO {
	@JsonProperty("id")
	lateinit	var 	identifier	: String

	@JsonProperty(value = "created_at")
	lateinit	var		createdAt	: String

	@JsonProperty(value = "updated_at")
	lateinit	var		updatedAt	: String
}