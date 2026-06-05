import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `java-gradle-plugin`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.samreceiver)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
        javaParameters = true
        freeCompilerArgs.addAll(
            "-Xsam-conversions=class",
            "-Xjsr305=strict",
            "-Xjspecify-annotations=strict",
        )
    }
}

samWithReceiver {
    annotation("org.gradle.api.HasImplicitReceiver")
}

repositories {
    google {
        content {
            includeGroupByRegex("com\\.android.*")
            includeGroupByRegex("com\\.google.*")
            includeGroupByRegex("androidx.*")
        }
    }
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("library") {
            id = "io.github.shadowrz.hanekokoro.framework.internal.library"
            implementationClass = "io.github.shadowrz.hanekokoro.framework.build.plugin.LibraryPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.dokka.plugin)
}
