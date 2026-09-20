import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import pro.jayeshseth.mugen.convention.libs

/**
 * A Gradle convention plugin for setting up a Compose Multiplatform (CMP) library module.
 *
 * This plugin applies essential configurations for a CMP library, including:
 * - The Jetpack Compose plugin (`org.jetbrains.compose`).
 * - The base Kotlin Multiplatform library convention plugin (`mugen.kmp.library`).
 * - The Kotlin Compose compiler plugin (`org.jetbrains.kotlin.plugin.compose`).
 *
 * It also adds a set of common dependencies required for Compose Multiplatform development
 * to the `commonMain` and `androidMain` source sets, such as Compose UI, Foundation, Material3,
 * and tooling libraries.
 */
class CmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.compose")
                apply("mugen.kmp.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.vanniktech.maven.publish")
            }

            dependencies {
                "androidMainImplementation"(libs.findLibrary("core-ktx").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-ui").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-foundation").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-material3").get())
                "commonMainImplementation"(
                    libs.findLibrary("jetbrains-compose-material-icons-core").get()
                )
                "commonMainImplementation"(
                    libs.findLibrary("jetbrains-compose-material-icons-extended").get()
                )
            }
        }
    }
}