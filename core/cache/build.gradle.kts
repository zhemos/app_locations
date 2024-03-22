plugins {
    alias(libs.plugins.locations.android.library)
    id("kotlin-kapt")
}

android {
    namespace = "com.zm.locations.core.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.datastore)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}