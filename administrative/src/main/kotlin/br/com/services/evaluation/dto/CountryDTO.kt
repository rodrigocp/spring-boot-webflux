package br.com.services.evaluation.dto

import br.com.services.evaluation.dto.base.DataTransfer
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY

@JsonInclude(JsonInclude.Include.NON_NULL)
class CountryDTO : DataTransfer {
	@JsonProperty(access = READ_ONLY)
	var	identifier	: Long?		= null

	@JsonProperty
	var	name		: String?	= null
}
