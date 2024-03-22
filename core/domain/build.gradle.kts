plugins {
    alias(libs.plugins.locations.android.library)
}

android {
    namespace = "com.zm.locations.core.domain"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {}