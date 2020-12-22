package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("city")
data class City(
	@Id			 			var	identifier	: Long?,
	@CreatedBy	 			var	account		: UUID?,
	@CreatedDate 			var	created		: LocalDateTime?,
	@Column		 			var	name		: String?,
	@Column("state_id")		var	state		: Long?,
	@Column("country_id")	var	country		: Long?
) : Domain