import core.ProjectPlugin
import core.kotlinOptions
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class KotlinLibraryConventionPlugin: ProjectPlugin() {

    override fun Project.beforeSetup() {
        apply(plugin = "java-library")
        apply(plugin = "org.jetbrains.kotlin.jvm")
    }

    override fun Project.setup() {
        extensions.getByType<JavaPluginExtension>().apply {
            sourceCompatibility = props.javaVersion
            targetCompatibility = props.javaVersion
        }
        kotlinOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}