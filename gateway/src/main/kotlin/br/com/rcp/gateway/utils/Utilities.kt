package br.com.rcp.gateway.utils

import com.fasterxml.jackson.databind.ObjectMapper

object Utilities {
	inline fun <reified T> ObjectMapper.writeJSON(value: T): String = writeValueAsString(value)
}