plugins {
    alias(libs.plugins.locations.android.library)
}

android {
    namespace = "com.zm.locations.core.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {}