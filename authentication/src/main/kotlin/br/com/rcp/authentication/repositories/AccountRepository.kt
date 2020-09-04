package br.com.rcp.authentication.repositories

import br.com.rcp.authentication.domains.Account
import br.com.rcp.commons.repository.AbstractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository

@Repository
class AccountRepository(@Autowired template: ReactiveMongoTemplate): AbstractRepository<Account>(template)