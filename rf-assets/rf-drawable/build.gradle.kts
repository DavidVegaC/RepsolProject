plugins {
    alias(libs.plugins.android.library.convention)
}

klean {
    name = "rf_drawable"
    features {
        useUiDependencies = false
    }
}