package core

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate
import java.io.File
import java.util.Properties

private val javaVersions = JavaVersion.values().associateBy { it.toString() }

private fun Project.loadProperties(name: String): Properties {
    return Properties().apply {
        val file = File(rootDir, name)
        load(file.inputStream())
    }
}

fun Project.toProperties() = ProjectProperties(this)

class ProjectProperties(
    project: Project
) {
    private val localProperties = project.loadProperties("local.properties")
    private val projectProperties = project.loadProperties("project.properties")

    val javaVersion: JavaVersion by lazy {
        val javaVersionStr = projectProperties.getProperty("javaVersion") ?: run {
            val versionsListStr = javaVersions.keys.joinToString("\n") { " * $it" }
            throw IllegalArgumentException("Invalid Java version. Valid values are: \n $versionsListStr")
        }
        javaVersions[javaVersionStr]!!
    }
    val minSdk: Int by lazy {
        projectProperties.getProperty("minSdk").toInt()
    }
    val targetSdk: Int by lazy {
        projectProperties.getProperty("targetSdk").toInt()
    }
    val composeCompilerVersion: String by projectProperties
    val versionName: String by localProperties
    val versionCode: Int by lazy {
        localProperties.getProperty("versionCode").toInt()
    }
    val keystorePath: String by localProperties
    val keystorePassword: String by localProperties
    val keyAlias: String by localProperties
    val keyPassword: String by localProperties
    private val groupId: String by lazy {
        projectProperties.getProperty("groupId").lowercase().replace(" ", "")
    }
    fun namespace(moduleName: String): String {
        if (moduleName.isBlank()) throw IllegalArgumentException("Module name cannot be blank")
        return "$groupId.${moduleName.lowercase().replace(" ", "")}"
    }
}