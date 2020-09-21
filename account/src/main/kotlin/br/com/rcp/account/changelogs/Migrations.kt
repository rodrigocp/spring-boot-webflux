package br.com.rcp.account.changelogs

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate
import com.github.cloudyrock.spring.v5.EnableMongock
import org.bson.Document
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
		template.getCollection("accounts").insertOne(
			Document()
				.append("username",		"administrator")
				.append("password", 	encoder.encode("root"))
				.append("enabled",		true)
				.append("locked",		false)
				.append("expired",		false)
				.append("credentials",	false)
		)
	}
}