package core

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

val Project.libs get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

operator fun VersionCatalog.get(key: String): Provider<MinimalExternalModuleDependency> {
    return findLibrary(key).get()
}