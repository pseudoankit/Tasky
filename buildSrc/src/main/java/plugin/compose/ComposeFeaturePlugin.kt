package plugin.compose

import Dependencies
import Modules
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import plugin.util.*

class ComposeFeaturePlugin : ComposeCorePlugin() {

    override fun apply(project: Project) {
        project.plugins.apply {
            apply("com.google.devtools.ksp")
        }

        val kotlinExtension = project.kotlinExtension
        val libraryExtension = project.libraryExtension
        libraryExtension.apply {
            libraryVariants.all {
                kotlinExtension.sourceSets.getByName(name) {
                    kotlin.srcDir("build/generated/ksp/$name/kotlin")
                }
            }
        }

        project.dependencies {
            with(Dependencies.Compose) {
                this@dependencies.implementation(ComposeDestinations)
                this@dependencies.ksp(ComposeDestinationsKsp)
            }
            with(Dependencies.Koin) {
                this@dependencies.implementation(Compose)
            }
            with(Modules.Core) {
                this@dependencies.implementationProject(CoreUI)
            }
        }

        super.apply(project)
    }
}