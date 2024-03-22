plugins {
    alias(libs.plugins.locations.android.feature)
}

android {
    namespace = "com.zm.locations.feature.location"
}

dependencies {
    implementation(projects.core.cache)
}