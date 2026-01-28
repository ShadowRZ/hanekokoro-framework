plugins {
    id("io.github.shadowrz.hanekokoro.framework.internal.library")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.maven.publish)
}

group = "io.github.shadowrz.hanekokoro.framework"
version = "0.2.2"

kotlin {
    android {
        namespace = "io.github.shadowrz.hanekokoro.framework.integration"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":runtime"))
            implementation(project(":annotations"))
            implementation(libs.compose.ui)
            implementation(libs.compose.runtime)
            implementation(libs.lifecycle.runtime)
            implementation(libs.lifecycle.runtime.compose)
            implementation(libs.decompose)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.ktx)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "integration", version.toString())

    pom {
        name = "Hanekokoro Framework Integration"
        description = "Application integration for Hanekokoro Framework"
        inceptionYear = "2025"
        url = "https://github.com/ShadowRZ/hanekokoro-framework"

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }

        developers {
            developer {
                id = "ShadowRZ"
                name = "Yorusaka Miyabi"
                url = "https://github.com/ShadowRZ"
            }
        }

        scm {
            url = "https://github.com/ShadowRZ/hanekokoro-framework"
            connection = "scm:git:https://github.com/ShadowRZ/hanekokoro-framework.git"
            developerConnection = "scm:git:https://github.com/ShadowRZ/hanekokoro-framework.git"
        }
    }
}
