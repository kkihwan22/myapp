rootProject.name = "myapp"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("de.fayard.refreshVersions") version "0.60.5"
}

include(
    "apps:tutorial-java",
    "apps:tutorial-spring")
