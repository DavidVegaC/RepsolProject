plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "rf_feature_manager"
    features {
        useUiDependencies = true
    }
}

dependencies {
    api(project(":rf-navigation"))
}