plugins {
    alias(libs.plugins.android.library.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "rf_navigation"
    features {
        useUiDependencies = true
    }
}