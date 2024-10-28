plugins {
    alias(libs.plugins.android.library.convention)
}

klean {
    name = "rf_core_data"
    features {
        useUiDependencies = false
        useNetworkDependencies = true
    }
}

dependencies {
    api(project(":rf-core-domain"))
    implementation(libs.androidx.datastore.preferences)
}