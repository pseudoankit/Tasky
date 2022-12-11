plugins {
    id(Plugins.Application)
    id(Plugins.Core)
}

android {
    namespace = "pseudoankit.droid.tasky"

    defaultConfig {
        applicationId = BuildConfig.App.ApplicationId
        versionCode = BuildConfig.App.VersionCode
        versionName = BuildConfig.App.VersionName
        testInstrumentationRunner = BuildConfig.App.TestInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.Compiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(BuildConfig.App.DefaultProguardOptimizeFile),
                BuildConfig.App.ProGuardRules
            )
        }
    }
}

dependencies {
    with(Modules.Core) {
        implementation(project(CoreUI))
    }

    with(Modules.Feature) {
        implementation(project(Authentication))
    }

    with(Dependencies.Koin) {
        implementation(Compose)
    }

    with(Dependencies.Compose) {
        implementation(Ui)
        implementation(Runtime)
        implementation(FoundationLayout)
        implementation(UiToolingPreview)
        implementation(UiTooling)
        implementation(Material3)
        implementation(Activity)
    }
}
