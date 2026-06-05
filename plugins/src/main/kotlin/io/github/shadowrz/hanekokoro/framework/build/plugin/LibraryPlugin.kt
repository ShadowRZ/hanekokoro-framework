package io.github.shadowrz.hanekokoro.framework.build.plugin

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.kotlin.multiplatform.library")
            pluginManager.apply("org.jetbrains.dokka")

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                jvm {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
                iosArm64()
                iosSimulatorArm64()

                compilerOptions {
                    allWarningsAsErrors.set(true)
                    explicitApi()
                }

                extensions.configure(KotlinMultiplatformAndroidLibraryExtension::class.java) {
                    namespace = "io.github.shadowrz.hanekokoro.framework.annotations"
                    compileSdk = 36
                    minSdk = 21
                }

                targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                    compilations.configureEach {
                        compileTaskProvider.configure {
                            compilerOptions {
                                jvmTarget.set(JvmTarget.JVM_11)
                            }
                        }
                    }
                }
            }
        }
    }
}
