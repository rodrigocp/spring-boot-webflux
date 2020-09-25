package br.com.rcp.session.domain

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class Session(
	@Indexed	var	token	: String?			= null,
	@TimeToLive	var	expires	: Long?				= null,
				var	issued	: String?			= null,
				var	data	: Map<String, Any>?	= null
)