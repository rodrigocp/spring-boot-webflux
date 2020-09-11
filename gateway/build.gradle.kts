plugins {
	id("org.springframework.boot")
	kotlin("plugin.spring")
	kotlin("jvm")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation(project(":commons"))
	implementation("org.jboss.resteasy:resteasy-client:3.0.6.Final")
	implementation("org.keycloak:keycloak-admin-client:6.0.1")
	implementation("org.keycloak:keycloak-spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
		mavenBom("org.keycloak.bom:keycloak-adapter-bom:11.0.2")
	}
}
