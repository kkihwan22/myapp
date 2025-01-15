plugins {
    id("basic.java-conventions")
    id("basic.test-conventions")
    id("basic.spring-conventions")
}

dependencies {
    implementation(Spring.boot.web)
    implementation(Spring.session.dataRedis)
    implementation(Spring.kafka)
    implementation(Spring.boot.data.redis)

    implementation("org.apache.kafka:kafka-streams")
    implementation("com.jayway.jsonpath:json-path:2.7.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}
