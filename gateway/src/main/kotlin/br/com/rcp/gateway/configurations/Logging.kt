package br.com.rcp.gateway.configurations

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
			val payload	= String(stream.toByteArray())
			val elapsed	= System.currentTimeMillis() - start;
			LoggerFactory.getLogger(Logging::class.java).info("response({} ms): identifier={}, uri={}, payload={}, audit={}", elapsed, identifier, status, payload, true)
		}

		private fun trace() {
			val	status	= delegate.statusCode
			val elapsed	= System.currentTimeMillis() - start;
			LoggerFactory.getLogger(Logging::class.java).info("response({} ms): identifier={}, uri={}, audit={}", elapsed, identifier, status, true)
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
			val payload	= String(stream.toByteArray())
			LoggerFactory.getLogger(Logging::class.java).info("request: identifier={}, method={}, uri={}, headers={}, payload={}, audit={}", identifier, method, path, headers, payload, true)
		}

		private fun trace() {
			val	method	= delegate.method
			val	path	= delegate.path.toString()
			val	headers	= delegate.headers
			LoggerFactory.getLogger(Logging::class.java).info("request: identifier={}, method={}, uri={}, headers={}, audit={}", identifier, method, path, headers, true)
		}
	}
}