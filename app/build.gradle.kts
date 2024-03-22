plugins {
    alias(libs.plugins.locations.android.application)
    alias(libs.plugins.locations.android.dagger)
}

android {
    namespace = "com.zm.locations"

    defaultConfig {
        applicationId = "com.zm.locations"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.settings)
    implementation(projects.feature.budget)
    implementation(projects.feature.board)
    implementation(projects.feature.location)
    implementation(projects.core.designsystem)

    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}