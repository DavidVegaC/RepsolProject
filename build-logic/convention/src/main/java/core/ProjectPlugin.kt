package core

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class ProjectPlugin: Plugin<Project> {

    private var propertiesNullable: ProjectProperties? = null
    protected val props: ProjectProperties get() = propertiesNullable!!

    override fun apply(target: Project) {
        propertiesNullable = target.toProperties()
        target.beforeSetup()
        target.setup()
    }

    abstract fun Project.beforeSetup()

    abstract fun Project.setup()
}