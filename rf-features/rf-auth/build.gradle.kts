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
    implementation(project(":rf-core-platform"))
    implementation(project(":rf-core-ui"))
    implementation((project(":rf-assets")))
    implementation(project(":rf-tools"))
    implementation(project(":rf-navigation"))
}