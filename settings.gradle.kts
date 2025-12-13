pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hanekokoro-framework"
include(":annotations")
include(":codegen")
include(":integration")
include(":markers")
include(":runtime")
include(":runtime-component")
include(":runtime-context")
include(":runtime-coroutines")
include(":runtime-lifecycle")
include(":runtime-navigation")
include(":runtime-plugin")
include(":runtime-presenter")
include(":runtime-renderer")
// Sample
include(":sample:app")
