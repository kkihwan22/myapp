plugins {
    id("basic.java-conventions")
    id("basic.test-conventions")
    id("basic.spring-conventions")
}

dependencies {
    implementation(Spring.boot.web)
    implementation(Spring.kafka)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}
