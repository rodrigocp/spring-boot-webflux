package br.com.rcp.gateway.configurations

import com.fasterxml.jackson.databind.ObjectMapper
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.server.reactive.*
import org.springframework.web.server.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.ByteArrayOutputStream
import java.nio.channels.Channels
import java.util.*

@Configuration
class Logging : WebFilter {
	override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
		val	start		= System.currentTimeMillis()
		val	identifier	= UUID.randomUUID()
		return chain.filter(ExchangeDecorator(identifier, exchange, start))
	}

	private class ExchangeDecorator(private val identifier: UUID, exchange: ServerWebExchange, private val start: Long) : ServerWebExchangeDecorator(exchange) {
		override fun getRequest(): ServerHttpRequest {
			return RequestLoggingInterceptor(identifier, super.getRequest())
		}

		override fun getResponse(): ServerHttpResponse {
			return ResponseLoggingInterceptor(identifier, super.getResponse(), start)
		}
	}

	private class ResponseLoggingInterceptor(private val identifier: UUID, delegate: ServerHttpResponse, private val start: Long) : ServerHttpResponseDecorator(delegate) {
		override fun writeAndFlushWith(body: Publisher<out Publisher<out DataBuffer>>): Mono<Void> {
			return writeWith(Flux.from(body).flatMapSequential { it })
		}

		override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
			return super.writeWith(Flux.from(body).doOnNext {
				trace(it)
			}.switchIfEmpty {
				trace()
				it.onComplete()
			})
		}

		private fun write(buffer: DataBuffer, stream: ByteArrayOutputStream) {
			Channels.newChannel(stream).write(buffer.asByteBuffer().asReadOnlyBuffer())
		}

		private fun trace(buffer: DataBuffer) {
			ByteArrayOutputStream().use {
				write(buffer, it)
				trace(it)
			}
		}

		private fun trace(stream: ByteArrayOutputStream) {
			val	status	= delegate.statusCode
			val res		= String(stream.toByteArray())
			val elapsed	= System.currentTimeMillis() - start;
			val	map		= mapOf("id" to identifier, "elapsed" to elapsed, "status" to status, "payload" to res, "audit" to true)
			val	log		= ObjectMapper().writeValueAsString(map)
			LoggerFactory.getLogger(Logging::class.java).info(log)
		}

		private fun trace() {
			val	status	= delegate.statusCode
			val elapsed	= System.currentTimeMillis() - start;
			val	map		= mapOf("id" to identifier, "elapsed" to elapsed, "status" to status, "audit" to true)
			val	log		= ObjectMapper().writeValueAsString(map)
			LoggerFactory.getLogger(Logging::class.java).info(log)
		}
	}

	private class RequestLoggingInterceptor(private val identifier: UUID, delegate: ServerHttpRequest) : ServerHttpRequestDecorator(delegate) {
		override fun getBody(): Flux<DataBuffer> {
			return super.getBody().doOnNext {
				trace(it)
			}.switchIfEmpty {
				trace()
				it.onComplete()
			}
		}

		private fun write(buffer: DataBuffer, stream: ByteArrayOutputStream) {
			Channels.newChannel(stream).write(buffer.asByteBuffer().asReadOnlyBuffer())
		}

		private fun trace(buffer: DataBuffer) {
			ByteArrayOutputStream().use {
				write(buffer, it)
				trace(it)
			}
		}

		private fun trace(stream: ByteArrayOutputStream) {
			val	method	= delegate.method
			val	path	= delegate.path.toString()
			val	headers	= delegate.headers
			val req		= String(stream.toByteArray())
			val	map		= mapOf("id" to identifier, "method" to method, "uri" to path, "headers" to headers, "payload" to req, "audit" to true)
			val	log		= ObjectMapper().writeValueAsString(map)
			LoggerFactory.getLogger(Logging::class.java).info(log)
		}

		private fun trace() {
			val	method	= delegate.method
			val	path	= delegate.path.toString()
			val	headers	= delegate.headers
			val	map		= mapOf("id" to identifier, "method" to method, "uri" to path, "headers" to headers, "audit" to true)
			val	log		= ObjectMapper().writeValueAsString(map)
			LoggerFactory.getLogger(Logging::class.java).info(log)
		}
	}
}