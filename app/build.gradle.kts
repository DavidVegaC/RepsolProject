import core.api
import core.implementation

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
    implementation(libs.gigya.sdk.core)

    api(project(":rf-feature-manager"))
    api(project(":rf-core-ui"))
    api(project(":rf-core-data"))
}
