package br.com.services.evaluation.domains

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table
data class Vehicle(
	@Id
	var	identifier		: Long,

	@Version
	var	version			: Long,

	@CreatedDate
	var	created			: OffsetDateTime,

	@Column("status")
	var	status			: String

//	@Column("plate")
//	var	plate			: String,
//
//	@Column("vin")
//	var	vin				: String,
//
//	@Column("renavam")
//	var	renavam			: String,
//
//	@Column("fuel")
//	var	fuel			: String,
//
//	@Column("gearbox")
//	var	gearbox			: String,
//
//	@Column("color")
//	var	color			: String,
//
//	@Column("mileage")
//	var	mileage			: Int,
//
//	@Column("fabrication_year")
//	var	fabricationYear	: Int,
//
//	@Column("model_year")
//	var	modelYear		: Int,
//
//	@Column("year")
//	var	client			: Long,
//
//	@Column("model_id")
//	var	model			: Long,
//
//	@Column("dealership_id")
//	var	dealership		: Long,
//
//	@Column("group_id")
//	var	group			: Long,
//
//	@Column("holding_id")
//	var	holding			: Long
)