package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("holding")
data class Holding(
	@Id			 			var	identifier	: Long?				= null,
	@CreatedBy	 			var	account		: UUID?				= null,
	@CreatedDate 			var	created		: LocalDateTime?	= null,
	@Column		 			var	name		: String?			= null,
	@Column					var	ftin		: String?			= null,
	@Column("country_id")	var	country		: Long?				= null,	// company country
	@Column("state_id")		var	state		: Long?				= null,	// company state or province
	@Column("city_id")		var	city		: Long?				= null	// company city
) : Domain