package br.com.rcp.session.domain

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
data class Session(
	@Indexed
	val	token	: String,
	val	data	: String,
	@TimeToLive
	val	expires	: Long
)