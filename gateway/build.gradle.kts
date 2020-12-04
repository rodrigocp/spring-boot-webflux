import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")							version "1.4.10"
	kotlin("plugin.spring")					version "1.4.10"
	id("org.springframework.boot")			version "2.4.0"
	id("io.spring.dependency-management")	version "1.0.10.RELEASE"
}

group						= "br.com.rcp"
version						= "1.0.0"
java.sourceCompatibility	= JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("net.logstash.logback:logstash-logback-encoder:6.4")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")
	implementation("io.github.resilience4j:resilience4j-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR9")
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