package br.com.rcp.account.repositories

import br.com.rcp.account.domains.Account
import br.com.rcp.commons.repository.AbstractRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository

@Repository
class AccountRepository(@Autowired template: ReactiveMongoTemplate): AbstractRepository<Account>(template, Account::class.java)