plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "rf_auth"
    features {
        useUiDependencies = true
        useNetworkDependencies = true
    }
}

dependencies {
    implementation(libs.gigya.sdk.core)

    implementation(project(":rf-core-domain"))
    implementation(project(":rf-core-data"))
    implementation(project(":rf-core-platform"))
    implementation(project(":rf-components"))
    implementation(project(":rf-core-ui"))
    implementation(project(":railway"))
    implementation((project(":rf-assets")))
    implementation(project(":rf-tools"))
    implementation(project(":rf-navigation"))
}