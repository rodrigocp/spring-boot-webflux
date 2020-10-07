import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("java-library")
	id("org.springframework.boot")
	kotlin("plugin.spring")
	kotlin("jvm")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("joda-time:joda-time:2.10.6")
}

tasks.withType<BootJar> {
	enabled			= false
	mainClassName	= "NONE"
}

tasks.withType<Jar> {
	enabled			= true
}