package br.com.rcp.commons.domain

import org.bson.types.ObjectId
import org.joda.time.LocalDateTime
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.mapping.Field

abstract class Domain {
	@Id
	lateinit	var		identifier	: ObjectId

	@Version
	lateinit	var		version		: Number

	@CreatedBy
	@Field("created_by")
	lateinit	var		createdBy	: String

	@LastModifiedBy
	@Field("modified_by")
	lateinit	var		modifiedBy	: String

	@CreatedDate
	@Field("created_at")
	lateinit	var		createdAt	: LocalDateTime

	@LastModifiedDate
	@Field("updated_at")
	lateinit	var		updatedAt	: LocalDateTime
}