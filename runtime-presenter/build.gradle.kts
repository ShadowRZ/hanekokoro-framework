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
        namespace = "io.github.shadowrz.hanekokoro.framework.runtime.presenter"
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":markers"))
            implementation(libs.compose.runtime)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "runtime-presenter", version.toString())

    pom {
        name = "Hanekokoro Framework Runtime Presenter"
        description = "Presenter Runtime code for Hanekokoro Framework"
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
