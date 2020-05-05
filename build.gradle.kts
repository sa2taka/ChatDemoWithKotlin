import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun


plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.siouan.frontend") version "2.0.0"
	war
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
	kotlin("plugin.jpa") version "1.3.71"
	idea

}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.session:spring-session-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}


idea {
	module {
		inheritOutputDirs = false
		outputDir = file("$buildDir/classes/kotlin/main")
	}
}

tasks.withType<BootRun> {
	systemProperties = mapOf(
		"spring.h2.console.enable" to true,
		"spring.h2.console.path" to "/h2-console"
	)
}

frontend {
	nodeDistributionProvided.set(false)
	nodeVersion.set("10.20.1")
	nodeDistributionUrl.set("https://nodejs.org/dist/v10.20.1/node-v10.20.1-darwin-x64.tar.gz")
	nodeInstallDirectory.set(project.layout.projectDirectory.dir("node"))

	yarnEnabled.set(true)
	yarnDistributionProvided.set(false)
	yarnVersion.set("1.22.4")
	yarnDistributionUrl.set("https://github.com/yarnpkg/yarn/releases/download/v1.22.4/yarn-v1.22.4.tar.gz")
	yarnInstallDirectory.set(project.layout.projectDirectory.dir("yarn"))

	installScript.set("install")
	cleanScript.set("run clean")
	assembleScript.set("run assemble")
	checkScript.set("run check")
	publishScript.set("run publish")
}