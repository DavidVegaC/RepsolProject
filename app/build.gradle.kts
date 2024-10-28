import core.api

plugins {
    alias(libs.plugins.android.application.convention)
    alias(libs.plugins.kotlin.compose)
}

klean {
    name = "repsolflotas"
    features {
        useUiDependencies = true
    }
}

dependencies {
    api(project(":rf-features:rf-auth"))
    api(project(":rf-features:rf-home"))
    implementation(project(":rf-core-ui"))
    implementation(project(":rf-core-data"))
}
