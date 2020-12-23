package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("city")
data class City(
	@Id			 			var	identifier	: Long?				= null,
	@CreatedBy	 			var	account		: UUID?				= null,
	@CreatedDate 			var	created		: LocalDateTime?	= null,
	@Column		 			var	name		: String?			= null,
	@Column("state_id")		var	state		: Long?				= null,
	@Column("country_id")	var	country		: Long?				= null
) : Domain