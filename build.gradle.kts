plugins {
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "io.github.gleidsonmt"
version = "1.1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
    // The line resolves the problem with the Java Language Server in VS Code, but it is unnecessary for Gradle run.
    // Without this line, Gradle run works, but the Java Language Server in VS Code reports “module not found” for module jars that are on the Gradle module path.
    // modularity.inferModulePath = true
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

javafx {
    version = "23.0.2"
    modules("javafx.controls", "javafx.web", "javafx.fxml", "javafx.swing")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
//    implementation(project(":blockcode"))
    implementation("org.jetbrains:annotations:24.0.1")
}

//test {
//    useJUnitPlatform()
//}



