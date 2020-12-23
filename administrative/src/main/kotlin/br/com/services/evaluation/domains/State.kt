package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("state")
data class State(
	@Id			 			var	identifier	: Long?				= null,
	@CreatedBy	 			var	account		: UUID?				= null,
	@CreatedDate 			var	created		: LocalDateTime?	= null,
	@Column		 			var	name		: String?			= null,
	@Column("country_id")	var	country		: Long?				= null
) : Domain