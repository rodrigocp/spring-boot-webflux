package br.com.rcp.account.configurations

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories
class ReactiveMongoDB(private var client: MongoClient) {
	@Bean
	fun reactiveMongoTemplate(): ReactiveMongoTemplate {
		return ReactiveMongoTemplate(client, "authorization")
	}
}