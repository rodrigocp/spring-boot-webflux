plugins {
	id("org.springframework.boot")
	kotlin("plugin.spring")
	kotlin("jvm")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation(project(":commons"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("com.github.cloudyrock.mongock:mongock-spring-v5:4.1.15")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
	implementation("com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.1.15")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
	}
}