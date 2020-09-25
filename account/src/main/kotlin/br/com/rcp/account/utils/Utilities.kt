package br.com.rcp.account.utils

import org.joda.time.LocalDateTime

object Utilities {
	fun LocalDateTime.toLocalDateTime() : java.time.LocalDateTime? {
		return java.time.LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute)
	}

}