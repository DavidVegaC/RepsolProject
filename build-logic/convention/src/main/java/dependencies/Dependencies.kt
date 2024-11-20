package dependencies

import core.androidTestImplementation
import core.debugImplementation
import core.get
import core.implementation
import core.ksp
import core.libs
import core.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.addAndroidCoreDependencies(){
    dependencies {
        implementation(libs["androidx-core-ktx"])
    }
}

fun Project.addNavigationDependencies(){
    dependencies {
        implementation(libs["androidx-navigation-compose"])
        implementation(libs["androidx-hilt-navigation-compose"])
    }
}

fun Project.addAndroidUiCoreDependencies(){
    dependencies {
        implementation(libs["androidx-appcompat"])
        implementation(libs["androidx-lifecycle-runtime-ktx"])
    }
}


fun Project.addComposeDependencies(){
    dependencies {
        implementation(platform(libs["androidx-compose-bom"]))
        implementation(libs["androidx-activity-compose"])
        implementation(libs["androidx-ui"])
        implementation(libs["androidx-ui-graphics"])
        implementation(libs["androidx-ui-tooling-preview"])
        implementation(libs["androidx-material3"])
    }
}

fun Project.addDaggerHiltDependencies(){
    dependencies {
        implementation(libs["google-dagger-hilt-android"])
        ksp(libs["google-dagger-hilt-compiler"])
    }
}

fun Project.addSerializationDependencies(){
    dependencies {
        implementation(libs["kotlinx-serialization-json"])
    }
}

fun Project.addTestDependencies(){
    dependencies {
        testImplementation(libs["junit"])
        androidTestImplementation(libs["androidx-junit"])
        androidTestImplementation(libs["androidx-espresso-core"])
    }
}

fun Project.addAndroidTestDependencies(){
    dependencies {
        androidTestImplementation(libs["androidx-junit"])
        androidTestImplementation(libs["androidx-espresso-core"])
    }
}

fun Project.addComposeTestDependencies(){
    dependencies {
        androidTestImplementation(platform(libs["androidx-compose-bom"]))
        androidTestImplementation(libs["androidx-ui-test-junit4"])
        debugImplementation(libs["androidx-ui-tooling"])
        debugImplementation(libs["androidx-ui-test-manifest"])
    }
}

fun Project.addNetworkDependencies(){
    dependencies {
        implementation(libs["retrofit"])
        implementation(libs["retrofit-serialization"])
        implementation(libs["retrofit-logging"])
        implementation(libs["okhttp"])
        implementation(libs["google-gson"])
    }
}

fun Project.addUiDependencies(){
    addAndroidCoreDependencies()
    addNavigationDependencies()
    addAndroidUiCoreDependencies()
    addComposeDependencies()
    addTestDependencies()
    addAndroidTestDependencies()
    addComposeTestDependencies()
    dependencies {
        implementation(libs["coil"])
        implementation(libs["coil-network-okhttp"])
    }
}

fun Project.addFeatureDependencies(){
    addUiDependencies()
}