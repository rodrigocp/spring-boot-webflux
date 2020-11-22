plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("org.liquibase.gradle") version "2.0.3"
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("io.r2dbc:r2dbc-postgresql")
	implementation("org.postgresql:postgresql")
	implementation("joda-time:joda-time:2.10.6")
	implementation("org.liquibase:liquibase-core:3.10.2")
	implementation("org.springframework.data:spring-data-r2dbc")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
	}
}
