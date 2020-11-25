package br.com.services.evaluation.domains

import br.com.services.evaluation.domains.base.Domain
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("group")
data class Group (
	@Id
	var	identifier		: Long,

	@Version
	var	version			: Long,

	@Column("created")
	var	createdAt		: LocalDateTime,

	@Column("account")
	var	createdBy		: UUID,

	@Column("name")
	var	name			: String,

	@Column("ein")
	var	ein				: String
) : Domain
