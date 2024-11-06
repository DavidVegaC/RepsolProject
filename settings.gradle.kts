pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Repsol Flotas"
include(":app")
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
include(":rf-core-domain")
include(":rf-core-data")
include(":rf-core-platform")
include(":rf-core-ui")
include(":rf-navigation")
include(":rf-feature-manager")
include(":railway")
include(":rf-features:rf-auth")
include(":rf-tools")
include(":rf-features:rf-home")
include(":rf-assets")
include(":rf-components")
