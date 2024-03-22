import com.android.build.gradle.LibraryExtension
import com.zm.locations.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("locations.android.library")
            apply("locations.android.dagger")
        }
        extensions.configure<LibraryExtension> {
            defaultConfig {
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildFeatures {
                viewBinding = true
            }
        }
        dependencies {
            add("implementation", project(":core:designsystem"))
            add("implementation", project(":core:common"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:data"))

            add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
        }
    }
}