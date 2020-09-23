package br.com.rcp.account.changelogs

import br.com.rcp.account.domains.Account
import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate
import com.github.cloudyrock.spring.v5.EnableMongock
import org.joda.time.LocalDateTime
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableMongock
@Configuration
@ChangeLog
class Migrations {
	private	val	encoder	: PasswordEncoder get() = PasswordEncoderFactories.createDelegatingPasswordEncoder()

	@ChangeSet(order = "001", author = "Rodrigo Pereira", id = "add_default_account")
	fun addDefaultAuthorities(template: MongockTemplate) {
		template.insert(
			Account().apply {
				username	= "administrator"
				fullname	= "System Administrator"
				email		= "administrator@system.com"
				password	= encoder.encode("root")
				enabled		= true
				createdAt	= LocalDateTime.now()
				updatedAt	= LocalDateTime.now()
				createdBy	= "system"
				modifiedBy	= "system"
				roles		= arrayListOf()
			}
		)
	}
}