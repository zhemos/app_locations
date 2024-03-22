import com.zm.locations.configureKotlinJvm
import com.zm.locations.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.jvm")
        }
        configureKotlinJvm()
        dependencies {
//            add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
        }
    }
}