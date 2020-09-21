package br.com.rcp.session.mappers

import br.com.rcp.session.domain.Session
import br.com.rcp.session.dto.SessionDTO

class SessionMapper() {
	fun toDTO(model: Session?): SessionDTO? {
		return model?.let {
			return SessionDTO(it.token, it.data, it.expires)
		}
	}

	fun toDomain(model: SessionDTO?): Session? {
		return model?.let {
			return Session(it.token, it.data, it.expires)
		}
	}
}