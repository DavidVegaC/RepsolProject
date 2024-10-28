plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "rf_core_ui"
    features {
        useUiDependencies = true
    }
}

dependencies {
    api(project(":rf-core-platform"))
}