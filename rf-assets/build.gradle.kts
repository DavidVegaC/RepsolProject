plugins {
    alias(libs.plugins.android.library.convention)
}

klean {
    name = "rf_assets"
    features {
        useUiDependencies = false
    }
}
