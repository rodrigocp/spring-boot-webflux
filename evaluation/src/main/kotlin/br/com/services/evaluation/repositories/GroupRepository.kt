package br.com.services.evaluation.repositories

import br.com.services.evaluation.domains.Group
import br.com.services.evaluation.repositories.base.AbstractRepository
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class GroupRepository(client: DatabaseClient) : AbstractRepository<Group>(client) {
	//
}
