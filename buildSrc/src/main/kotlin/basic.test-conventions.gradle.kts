plugins {
    java
    idea
}

repositories {
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }

    maxHeapSize = "2g"
}



dependencies {
    // junit
    testImplementation(Testing.junit.jupiter)
    testRuntimeOnly(Testing.junit.jupiter.engine)

    // mockito
    testImplementation(Testing.mockito.core)
    testImplementation(Testing.mockito.junitJupiter)
    testImplementation(Testing.assertj.core)

    testCompileOnly("org.projectlombok:lombok:_")
    testAnnotationProcessor("org.projectlombok:lombok:_")

    /**
    project.afterEvaluate {
        if (project.pluginManager.hasPlugin("custom.spring-conventions")) {
            testImplementation(Spring.boot.test)
        }
    }
    */
}