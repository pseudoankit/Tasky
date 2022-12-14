plugins {
    id(Plugins.AndroidLibrary)
    id(Plugins.Core)
}

android {
    namespace = "pseudoankit.droid.navigation"
}

dependencies {
    with(Modules.Core) {
        implementation(project(CoreUi))
        implementation(project(AgendaManager))
    }

    with(Dependencies.Compose) {
        implementation(ComposeDestinations)
    }

    with(Modules.Feature) {
        implementation(project(Authentication))
        implementation(project(Home))
        implementation(project(Event))
        implementation(project(Reminder))
        implementation(project(Task))
    }
}