package br.com.rcp.account.configurations

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories
class BlockingMongoDB(private var client: MongoClient) {
	@Bean
	fun defaultMongoTemplate(): MongoTemplate {
		return MongoTemplate(client, "authorization")
	}
}