plugins {
    id("basic.java-conventions")
    id("basic.test-conventions")
}

dependencies {
    implementation("org.slf4j:slf4j-simple:_")
    implementation("org.apache.kafka:kafka-clients:2.8.2")
}