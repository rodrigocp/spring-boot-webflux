package br.com.rcp.commons.handler

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

interface Handler {
	suspend	fun	find	(request: ServerRequest)	: ServerResponse
	suspend	fun	fetch	(request: ServerRequest)	: ServerResponse
	suspend	fun	persist	(request: ServerRequest)	: ServerResponse
	suspend	fun	update	(request: ServerRequest)	: ServerResponse
	suspend	fun	remove	(request: ServerRequest)	: ServerResponse

}