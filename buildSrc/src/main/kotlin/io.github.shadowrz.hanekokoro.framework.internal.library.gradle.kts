import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.dokka")
}
kotlin {
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    android {
        namespace = "io.github.shadowrz.hanekokoro.framework.annotations"
        compileSdk = 36
        minSdk = 21

        compilations.configureEach {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget = JvmTarget.JVM_1_8
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    compilerOptions {
        allWarningsAsErrors = true
    }
}

dokka {
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(true)
    }
    dokkaSourceSets.configureEach {
        externalDocumentationLinks.register("kotlinx-coroutines") {
            url("https://kotlinlang.org/api/kotlinx.coroutines/")
            packageListUrl("https://kotlinlang.org/api/kotlinx.coroutines/package-list")
        }
        externalDocumentationLinks.register("androidx") {
            url("https://developer.android.google.cn/reference/kotlin/")
            packageListUrl("https://developer.android.google.cn/reference/kotlin/androidx/package-list")
        }
    }
    pluginsConfiguration.html {
        footerMessage.set("2025 Yorusaka Miyabi")
    }
}
