import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")							version "1.4.10"
	kotlin("plugin.spring")					version "1.4.10"
	id("org.springframework.boot")			version "2.4.0"
	id("io.spring.dependency-management")	version "1.0.10.RELEASE"
}

group	= "br.com.rcp"
version	= "1.0.0"

repositories {
	mavenCentral()
	jcenter()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("joda-time:joda-time:2.10.6")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("com.github.cloudyrock.mongock:mongock-spring-v5:4.1.15")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.1.15")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2020.0.0-M5")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		sourceCompatibility	= "11"
		targetCompatibility	= "11"
		jvmTarget			= "11"
	}
}

tasks.test {
	useJUnitPlatform()
}

tasks.withType<Test> {
	useJUnitPlatform()
}