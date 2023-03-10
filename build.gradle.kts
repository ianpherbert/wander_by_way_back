import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.herbert.travelapp.api"
version = "1.0.0-SNAPSHOT"
description = "management"

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.0"
    id("io.github.kobylynskyi.graphql.codegen") version "5.4.1"
}

group = "com.herbert"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("io.mongock:mongock-bom:5.0.34")
    }
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mapstruct:mapstruct:1.5.0.Beta1")
    implementation("io.mongock:mongodb-springdata-v3-driver")
    implementation("io.mongock:mongock-springboot")
    implementation("javax.validation:validation-api:2.0.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.0.Beta1")
    implementation("com.byteowls:jopencage:1.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<io.github.kobylynskyi.graphql.codegen.gradle.GraphQLCodegenGradleTask>("graphqlCodegen") {
    graphqlSchemaPaths = listOf("$projectDir/src/main/resources/graphql/travel.graphqls")
    outputDir = File("$buildDir/generated")
    packageName = "com.herbert.graphql.model"
}

sourceSets {
    getByName("main").java.srcDirs("$buildDir/generated")
}

tasks.withType(JavaCompile::class.java) {
    dependsOn("graphqlCodegen")
}

tasks.withType(org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask::class.java) {
    dependsOn("graphqlCodegen")
}