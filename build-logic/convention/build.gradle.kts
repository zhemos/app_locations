import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.zm.locations.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "locations.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidDagger") {
            id = "locations.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }
        register("androidLibrary") {
            id = "locations.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "locations.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}