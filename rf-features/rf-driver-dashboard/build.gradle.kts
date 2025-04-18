plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "rf_driver_dashboard"
    features {
        useUiDependencies = true
        useNetworkDependencies = true
    }
}

dependencies {
    api(project(":rf-navigation"))
    api(project(":rf-core-platform"))
    api(project(":rf-core-ui"))
    api(project(":rf-core-domain"))
    api(project(":rf-core-data"))
    api(project(":rf-components"))
    api(project(":rf-assets"))
    api(project(":rf-tools"))
    api(project(":railway"))
}