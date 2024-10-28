import com.android.build.api.dsl.ApplicationExtension
import core.ProjectPlugin
import core.implementation
import core.kotlinOptions
import dependencies.addAndroidCoreDependencies
import dependencies.addDaggerHiltDependencies
import dependencies.addSerializationDependencies
import dependencies.addUiDependencies
import extensions.KleanExtension
import extensions.core.ifNotBlank
import extensions.core.lazyOnChange
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class ApplicationConventionPlugin: ProjectPlugin() {

    override fun Project.beforeSetup() {
        apply(plugin = "com.android.application")
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
            signingConfigs {
                create("signingConfig") {
                    storeFile = file(props.keystorePath)
                    storePassword = props.keystorePassword
                    keyAlias = props.keyAlias
                    keyPassword = props.keyPassword
                }
            }
            defaultConfig {
                minSdk = props.minSdk
                targetSdk = props.targetSdk
                versionCode = props.versionCode
                versionName = props.versionName
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    signingConfig = signingConfigs.getByName("signingConfig")
                }
            }
            compileOptions {
                sourceCompatibility = props.javaVersion
                targetCompatibility = props.javaVersion
            }
            kotlinOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = props.composeCompilerVersion
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
            dependencies {
                implementation(project(":rf-navigation"))
                addAndroidCoreDependencies()
                addDaggerHiltDependencies()
                addSerializationDependencies()
            }
        }
        tasks.withType<JavaCompile> {
            options.compilerArgs.add("-Xlint:deprecation")
        }
    }

    private fun Project.moduleConfigObservers() {
        extensions.getByType<KleanExtension>().apply {
            nameProperty.ifNotBlank {
                android {
                    namespace = props.namespace(it)
                    defaultConfig {
                        applicationId = props.namespace(it)
                    }
                }
            }
            featuresExtensions.lazyOnChange {
                if (it.useUiDependencies) {
                    addUiDependencies()
                }
            }
        }
    }

    private fun Project.android(block: ApplicationExtension.() -> Unit) {
        extensions.getByType<ApplicationExtension>().block()
    }
}