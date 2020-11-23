package br.com.services.evaluation.extensions

import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.r2dbc.core.DatabaseClient.DeleteSpec
import org.springframework.data.r2dbc.core.DatabaseClient.UpdateSpec
import org.springframework.data.r2dbc.core.FetchSpec
import java.util.stream.Collectors

suspend fun <T> FetchSpec<T>.await() : List<T> {
	return all().collect(Collectors.toList()).awaitSingle()
}

suspend fun UpdateSpec.await() {
	then().awaitFirstOrNull()
}

suspend fun DeleteSpec.await() {
	then().awaitFirstOrNull()
}