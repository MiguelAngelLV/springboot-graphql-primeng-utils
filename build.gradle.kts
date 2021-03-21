import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    maven
    `maven-publish`
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
    implementation("org.springframework.boot:spring-boot-starter-web:2.4.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.4.3")
    implementation("org.testng:testng:7.1.0")


    //Graphql
    val graphqlVersion = "11.0.0"
    implementation("com.graphql-java-kickstart", "graphql-spring-boot-starter", graphqlVersion)


    //QueryDSL
    val queryDslVersion = "4.3.1"
    implementation ("com.querydsl", "querydsl-apt", queryDslVersion)
    implementation("com.querydsl", "querydsl-jpa", queryDslVersion)
    implementation ("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
    kapt("com.querydsl:querydsl-apt:4.3.1:jpa")


}
