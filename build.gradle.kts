plugins {
    alias(libs.plugins.maven.publish) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("org.jetbrains.dokka")
}

dependencies {
    dokka(project(":annotations"))
    dokka(project(":integration"))
    dokka(project(":markers"))
    dokka(project(":runtime"))
    dokka(project(":runtime-component"))
    dokka(project(":runtime-context"))
    dokka(project(":runtime-coroutines"))
    dokka(project(":runtime-lifecycle"))
    dokka(project(":runtime-navigation"))
    dokka(project(":runtime-plugin"))
    dokka(project(":runtime-presenter"))
    dokka(project(":runtime-renderer"))
}

dokka {
    dokkaPublications.html {
        failOnWarning.set(true)
    }
    pluginsConfiguration.html {
        footerMessage.set("2025 Yorusaka Miyabi")
    }
}
