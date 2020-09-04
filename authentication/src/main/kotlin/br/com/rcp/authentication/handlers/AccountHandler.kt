package br.com.rcp.authentication.handlers

import br.com.rcp.authentication.domains.Account
import br.com.rcp.authentication.dto.AccountDTO
import br.com.rcp.authentication.mapper.AccountMapper
import br.com.rcp.authentication.repositories.AccountRepository
import br.com.rcp.commons.handler.Handler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountHandler(@Autowired repository: AccountRepository): Handler<Account, AccountDTO>(repository, AccountMapper())