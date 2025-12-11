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
include(":runtime")
include(":runtime-component")
include(":runtime-core")
include(":runtime-lifecycle")
include(":runtime-plugin")
include(":runtime-presenter")
