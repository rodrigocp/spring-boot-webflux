package br.com.services.evaluation

import br.com.services.evaluation.repositories.CountryRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.test.context.junit.jupiter.SpringExtension


@WebFluxTest(value = [CountryRepository::class])
@ExtendWith(SpringExtension::class)
class GroupTest {
	@MockBean
	private lateinit var client		: DatabaseClient

	@Autowired
	private	lateinit var repository	: CountryRepository

	@Test
	fun findAll() {
		runBlocking {
			repository.find()
		}
	}
}
