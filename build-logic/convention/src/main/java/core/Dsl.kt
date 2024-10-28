package core

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

fun Project.kotlinOptions(action: KotlinJvmCompilerOptions.() -> Unit) {
    tasks.withType(KotlinJvmCompile::class.java).configureEach {
        action(this.compilerOptions)
    }
}

fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    "implementation"(dependencyNotation)
}

fun DependencyHandlerScope.ksp(dependencyNotation: Any) {
    "ksp"(dependencyNotation)
}

fun <T: Any> DependencyHandlerScope.testImplementation(dependency: Provider<T>) {
    addProvider("testImplementation", dependency)
}

fun <T: Any> DependencyHandlerScope.androidTestImplementation(dependency: Provider<T>) {
    addProvider("androidTestImplementation", dependency)
}

fun <T: Any> DependencyHandlerScope.debugImplementation(dependency: Provider<T>) {
    addProvider("debugImplementation", dependency)
}

fun DependencyHandlerScope.api(dependencyNotation: Any) {
    "api"(dependencyNotation)
}