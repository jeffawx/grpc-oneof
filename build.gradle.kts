import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.airwallex.grpc-spring") version "1.2.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
	mavenCentral()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all", "-opt-in=kotlin.RequiresOptIn")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
