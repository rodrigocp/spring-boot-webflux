package br.com.services.evaluation.domains.base

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSupertypeOf
import kotlin.reflect.full.memberProperties

interface Domain {
	fun <R : Any> copy(source: R, nullable: Boolean = true, vararg props: KProperty<*>) {
		if (source::class == this::class) {
			val	mutable		= this::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
			val properties	= if (props.isEmpty()) source::class.memberProperties else props.toList()
			mutable.forEach { target -> properties.find { it.name == target.name && target.returnType.isSupertypeOf(it.returnType)}?.let { copy(it.getter.call(source), nullable, target) } }
		} else {
			throw RuntimeException("Source and target class differs!")
		}
	}

	private fun <T: Any> copy(copy: T?, nullable: Boolean, target: KMutableProperty<*>) {
		if (!nullable || (nullable && copy != null)) {
			target.setter.call(this, copy)
		}
	}
}