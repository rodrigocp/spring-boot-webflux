import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("joda-time:joda-time:2.10.6")
}

dependencyManagement {
	imports { mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE") }
}

tasks.withType<BootJar> {
	enabled			= false
	mainClassName	= "NONE"
}

tasks.withType<Jar> {
	enabled			= true
}