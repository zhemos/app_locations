plugins {
    alias(libs.plugins.locations.android.library)
}

android {
    namespace = "com.zm.locations.core.designsystem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(projects.core.common)

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.lifecycle.ext)
    api(libs.lifecycle.viewmodel)
    api(libs.lifecycle.runtime)
    api(libs.lifecycle.viewmodel.savedstate)
    api(libs.navigation.fragment)
    api(libs.navigation.ui)
}