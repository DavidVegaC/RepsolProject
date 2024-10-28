plugins {
    alias(libs.plugins.android.library.convention)
}

klean {
    name = "rf_core_domain"
    features {
        useUiDependencies = false
    }
}

dependencies {
    api(project(":railway"))
}