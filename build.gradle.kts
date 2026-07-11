plugins {
    id("java")
    id("io.gatling.gradle") version "3.13.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// The gatling plugin automatically handles dependencies.
// You don't need extra junit or gatling-core entries for basic runs.
dependencies {
}



tasks.test {
    useJUnitPlatform()
}
