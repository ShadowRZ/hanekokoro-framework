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
include(":runtime")
include(":runtime-component")
include(":runtime-context")
include(":runtime-lifecycle")
include(":runtime-plugin")
include(":runtime-presenter")
include(":runtime-ui")
