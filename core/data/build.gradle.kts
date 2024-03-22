plugins {
    alias(libs.plugins.locations.android.library)
}

android {
    namespace = "com.zm.locations.core.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.cache)
    implementation(projects.core.domain)
}