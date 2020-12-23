package br.com.services.evaluation.repositories

import br.com.services.evaluation.domains.Holding
import br.com.services.evaluation.repositories.base.AbstractRepository
import org.springframework.stereotype.Repository
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate as Template

@Repository
class HoldingRepository(template: Template) : AbstractRepository<Holding>(template) {
	//
}