package br.com.rcp.account.domains.base

import org.bson.types.ObjectId
import org.joda.time.LocalDateTime

interface Record {
	var	identifier	: ObjectId
	var	version		: Number
	var	createdBy	: String
	var	modifiedBy	: String
	var	createdAt	: LocalDateTime
	var	updatedAt	: LocalDateTime
}