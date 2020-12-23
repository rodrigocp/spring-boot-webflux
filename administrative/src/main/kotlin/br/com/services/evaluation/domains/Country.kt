package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("country")
data class Country(
	@Id			 var	identifier	: Long?				= null,
	@CreatedBy	 var	account		: UUID?				= null,
	@CreatedDate var	created		: LocalDateTime?	= null,
	@Column		 var	name		: String?			= null
) : Domain
