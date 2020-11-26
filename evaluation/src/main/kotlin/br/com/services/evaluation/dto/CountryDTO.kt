package br.com.services.evaluation.dto

import br.com.services.evaluation.dto.base.DataTransfer
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
object CountryDTO : DataTransfer {
	@JsonProperty
	var	identifier	: Long		= 0L

	@JsonProperty
	var	version		: Long		= 0L

	@JsonProperty
	var	name		: String	= ""
}
