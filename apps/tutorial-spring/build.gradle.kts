plugins {
    id("basic.java-conventions")
    id("basic.test-conventions")
    id("basic.spring-conventions")
}

dependencies {
    implementation(Spring.boot.web)
}
