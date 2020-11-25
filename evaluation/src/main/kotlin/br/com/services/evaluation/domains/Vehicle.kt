package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("vehicle")
data class Vehicle (
	@Id
	var	identifier		: Long,

	@Version
	var	version			: Long,

	@Column("created")
	var	createdAt		: LocalDateTime,

	@Column("account")
	var	createdBy		: UUID,

	@Column("status")
	var	status			: String,

	@Column("plate")
	var	plate			: String,

	@Column("vin")
	var	vin				: String,

	@Column("mileage")
	var	mileage			: Int,

	@Column("year")
	var	year			: Int
) : Domain
