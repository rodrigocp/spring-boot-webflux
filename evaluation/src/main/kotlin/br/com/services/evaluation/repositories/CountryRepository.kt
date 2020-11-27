package br.com.services.evaluation.repositories

import br.com.services.evaluation.domains.Country
import br.com.services.evaluation.repositories.base.AbstractRepository
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class CountryRepository(client: DatabaseClient) : AbstractRepository<Country>(client) {
	//
}