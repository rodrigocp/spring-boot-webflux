package br.com.services.evaluation.mapper

import br.com.services.evaluation.domains.Group
import br.com.services.evaluation.dto.GroupDTO
import br.com.services.evaluation.mapper.base.Mapper

object GroupMapper : Mapper<Group, GroupDTO> {
	override fun convert(value: Group): GroupDTO {
		TODO("Not yet implemented")
	}

	override fun convert(value: GroupDTO): Group {
		TODO("Not yet implemented")
	}
}
