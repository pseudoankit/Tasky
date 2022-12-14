package plugin.compose

import Dependencies
import Modules
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import plugin.util.*

/**
 * Plugin containing setup for adding compose core deps with navigation lib setup
 * Also includes coreUi and koin deps
 * ###Applicable only for library level module
 */
class ComposeFeaturePlugin : ComposeCorePlugin() {

    override fun apply(project: Project) {
        project.plugins.apply {
            apply("com.google.devtools.ksp")
        }

        project.libraryExtension?.apply {
            libraryVariants.all {
                project.kotlinExtension.sourceSets.getByName(name) {
                    kotlin.srcDir("build/generated/ksp/$name/kotlin")
                }
            }
        }

        project.dependencies {
            with(Dependencies.Compose) {
                this@dependencies.implementation(ComposeDestinations)
                this@dependencies.ksp(ComposeDestinationsKsp)
                this@dependencies.implementation(OrbitMvi)
            }
            with(Dependencies.Koin) {
                this@dependencies.implementation(Compose)
            }
            with(Modules.Core) {
                this@dependencies.implementationProject(DesignSystem)
                this@dependencies.implementationProject(CoreUi)
            }
        }

        super.apply(project)
    }
}