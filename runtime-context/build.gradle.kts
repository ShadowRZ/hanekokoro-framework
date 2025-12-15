plugins {
    id("io.github.shadowrz.hanekokoro.framework.internal.library")
    alias(libs.plugins.maven.publish)
}

group = "io.github.shadowrz.hanekokoro.framework"
version = "0.2.0"

kotlin {
    android {
        namespace = "io.github.shadowrz.hanekokoro.framework.runtime.context"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":annotations"))
            implementation(libs.androidx.compose.runtime.annotation)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "runtime-context", version.toString())

    pom {
        name = "Hanekokoro Framework Runtime Context"
        description = "Context Runtime code for Hanekokoro Framework"
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
