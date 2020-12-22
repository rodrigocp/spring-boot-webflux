package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("holding")
data class Holding(
	@Id			 			var	identifier	: Long?,
	@CreatedBy	 			var	account		: UUID?,
	@CreatedDate 			var	created		: LocalDateTime?,
	@Column		 			var	name		: String?,
	@Column					var	ftin		: String?,
	@Column("country_id")	var	country		: Long?,	// company country
	@Column("state_id")		var	state		: Long?,	// company state or province
	@Column("city_id")		var	city		: Long?		// company city
) : Domain