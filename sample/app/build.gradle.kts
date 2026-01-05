import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "io.github.shadowrz.hanekokoro.framework.sample.app"

plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
}

android {
    namespace = "io.github.shadowrz.hanekokoro.framework.sample.app"

    defaultConfig {
        applicationId = "io.github.shadowrz.hanekokoro.framework.sample.app"
        versionCode = 1
        versionName = "1.0"

        compileSdk = 36
        targetSdk = 36
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

metro {
    debug = true
}

dependencies {
    ksp(project(":codegen"))

    implementation(project(":annotations"))
    implementation(project(":integration"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.startup)
    implementation(libs.decompose.compose)
    implementation(libs.metrox.android)
}
