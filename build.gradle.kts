import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
    kotlin("kapt") version "1.4.31"
}

group = "org.miguelangellv.springboot"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
}

kotlin {
    explicitApi()
}



tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {


    //Spring
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    //QueryDSL
    val queryDslVersion = "4.3.1"
    implementation ("com.querydsl", "querydsl-apt", queryDslVersion)
    implementation("com.querydsl", "querydsl-jpa", queryDslVersion)
    implementation ("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
    kapt("com.querydsl:querydsl-apt:4.3.1:jpa")


}
