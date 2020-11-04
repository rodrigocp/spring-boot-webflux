package br.com.rcp.account.domains

import br.com.rcp.account.domains.base.Record
import org.bson.types.ObjectId
import org.joda.time.LocalDateTime
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("accounts")
@TypeAlias("account")
data class Account(
	@Id
	override var identifier		: ObjectId		= ObjectId.get(),

	@Version
	override var version		: Number		= 0,

	@CreatedBy
	override var createdBy		: String		= "",

	@LastModifiedBy
	override var modifiedBy		: String		= "",

	@CreatedDate
	@Field("created_at")
	override var createdAt		: LocalDateTime	= LocalDateTime.now(),

	@LastModifiedDate
	@Field("updated_at")
	override var updatedAt		: LocalDateTime	= LocalDateTime.now(),

	@Indexed(unique = true)
	var	email		: String,

	@Indexed(unique = true)
	var	username	: String,

	@Field("full_name")
	var	name		: String,

	@Field("password")
	var	password	: String,

	@Field("enabled")
	var	enabled		: Boolean,

	@Field("roles")
	var	roles		: MutableList<String>
) : Record