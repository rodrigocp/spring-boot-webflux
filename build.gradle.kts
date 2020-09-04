import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {
	id("org.springframework.boot")			version "2.3.3.RELEASE"		apply false
	id("io.spring.dependency-management")	version "1.0.10.RELEASE"	apply false
	kotlin("jvm")							version "1.3.72"			apply false
	kotlin("plugin.spring")					version "1.3.72"			apply false
}

allprojects {
	group						= "br.com.rcp"
	version						= "1.0.0"

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility		= "11"
		targetCompatibility		= "11"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs 	= listOf("-Xjsr305=strict")
			jvmTarget			= "11"
			incremental			= false
		}
	}
}

subprojects {
	repositories {
		mavenCentral()
	}

	apply {
		plugin("io.spring.dependency-management")
	}
}