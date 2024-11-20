plugins {
    alias(libs.plugins.android.library.convention)
}

klean {
    name = "rf_core_data"
    features {
        useUiDependencies = false
        useNetworkDependencies = true
    }
}

android {

    buildTypes {
        getByName("debug"){
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://b74c7b25-2c11-4eb9-89b1-cb0009f1710b.mock.pstmn.io\""
            )
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    android.buildFeatures.buildConfig = true
}

dependencies {
    api(project(":rf-core-domain"))
    implementation(libs.androidx.datastore.preferences)
}