import core.api

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

    api(project(":rf-features:rf-auth"))
    api(project(":rf-features:rf-gestor-dashboard"))
    api(project(":rf-features:rf-driver-dashboard"))
    api(project(":rf-features:rf-home"))
}