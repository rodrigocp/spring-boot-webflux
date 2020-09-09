package br.com.rcp.account.domains

import br.com.rcp.commons.domain.Domain
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
open class Account : Domain() {
	@Indexed(unique = true)
	var		username	: String				= ""
	var		fullname	: String				= ""
	var 	email		: String				= ""
	var		password	: String				= ""
	var		enabled		: Boolean				= false
	var		roles		: MutableList<String>	= mutableListOf()
}