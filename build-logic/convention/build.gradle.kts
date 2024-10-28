import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    `kotlin-dsl`
}

group = "com.repsol.build_logic"

val javaVersions = JavaVersion.values().associateBy { it.toString() }
val file = rootProject.projectDir.parentFile.resolve( "project.properties")
val projectProperties = loadProperties(file.path)
val javaVersion = javaVersions[projectProperties.getProperty("javaVersion")] ?: run {
    val versionsListStr = javaVersions.keys.joinToString("\n") { " * $it" }
    throw IllegalArgumentException("Invalid Java version. Valid values are: \n $versionsListStr")
}

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(javaVersion.toString())
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.android.application.plugin)
    compileOnly(libs.android.library.plugin)
    compileOnly(libs.jetbrains.kotlin.android.plugin)
    compileOnly(libs.google.ksp.plugin)
    compileOnly(libs.google.dagger.hilt.plugin)
    compileOnly(libs.jetbrains.kotlin.jvm.plugin)
    implementation(libs.jetbrains.kotlin.serialization.plugin)
}

gradlePlugin {
    plugins {
        register("applicationConventionPlugin") {
            id = "application.convention.plugin"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("kotlinLibConventionPlugin") {
            id = "kotlin.library.convention.plugin"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidLibConventionPlugin") {
            id = "android.library.convention.plugin"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("featureConventionPlugin") {
            id = "feature.convention.plugin"
            implementationClass = "FeatureConventionPlugin"
        }
    }
}