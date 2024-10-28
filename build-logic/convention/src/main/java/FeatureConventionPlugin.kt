import com.android.build.api.dsl.LibraryExtension
import core.ProjectPlugin
import core.api
import core.implementation
import core.kotlinOptions
import dependencies.*
import extensions.KleanExtension
import extensions.core.ifNotBlank
import extensions.core.lazyOnChange
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class FeatureConventionPlugin: ProjectPlugin() {

    override fun Project.beforeSetup() {
        apply(plugin = "com.android.library")
        apply(plugin = "org.jetbrains.kotlin.android")
        apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
        apply(plugin = "com.google.devtools.ksp")
        apply(plugin = "com.google.dagger.hilt.android")
        extensions.add(
            KleanExtension.NAME,
            KleanExtension::class.java
        )
        moduleConfigObservers()
    }

    override fun Project.setup() {
        android {
            compileSdk = props.targetSdk
            defaultConfig {
                minSdk = props.minSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            compileOptions {
                sourceCompatibility = props.javaVersion
                targetCompatibility = props.javaVersion
            }
            kotlinOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
            dependencies {
                api(project(":rf-core-data"))
                api(project(":rf-core-ui"))
                implementation(project(":rf-navigation"))
                addAndroidCoreDependencies()
                addDaggerHiltDependencies()
                addNavigationDependencies()
                addSerializationDependencies()
            }
        }
    }

    private fun Project.moduleConfigObservers() {
        extensions.getByType<KleanExtension>().apply {
            nameProperty.ifNotBlank {
                android {
                    namespace = props.namespace(it)
                }
            }
            featuresExtensions.lazyOnChange {
                if (it.useUiDependencies) {
                    addUiDependencies()
                    android {
                        defaultConfig {
                            vectorDrawables {
                                useSupportLibrary = true
                            }
                        }
                        buildFeatures {
                            compose = true
                        }
                        composeOptions {
                            kotlinCompilerExtensionVersion = props.composeCompilerVersion
                        }
                    }
                }
                if (it.useNetworkDependencies) {
                    addNetworkDependencies()
                }
            }
        }
    }

    private fun Project.android(block: LibraryExtension.() -> Unit) {
        extensions.getByType<LibraryExtension>().block()
    }
}