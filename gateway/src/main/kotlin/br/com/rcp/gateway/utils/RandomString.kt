package br.com.rcp.gateway.utils

import java.security.SecureRandom
import java.util.*

object RandomString {
	private	val	upper							= "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	private	val	lower							= upper.toLowerCase(Locale.ROOT)
	private	val	digits							= "0123456789"
	private	val alphanumeric					= upper + lower + digits

	fun generate(length: Int, random: Random, dictionary: String): String {
		if (length >= 1 && dictionary.length >= 2) {
			val	symbols								= dictionary.toCharArray()
			val	buffer								= CharArray(length)
			for (i in buffer.indices) buffer[i]	= symbols[random.nextInt(symbols.size)]
			return buffer.toString()
		} else {
			throw RuntimeException("Length or symbols does not fit requirement!")
		}
	}

	fun generate(length: Int, random: Random): String {
		return generate(length, random, alphanumeric)
	}

	fun generate(length: Int): String {
		return generate(length, SecureRandom())
	}

	fun generate(): String {
		return generate(20)
	}
}