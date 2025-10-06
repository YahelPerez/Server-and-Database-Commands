plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // For parsing JSON responses from the API
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.formdev:flatlaf:3.2.5")

    // JDBC driver for PostgreSQL, needed only at runtime
    runtimeOnly("org.postgresql:postgresql:42.6.0")

    // --- Existing test dependencies ---
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}