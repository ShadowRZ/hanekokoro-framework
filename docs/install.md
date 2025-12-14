# Installation

Hanekokoro Framework is avaliable on Maven Central.

## Runtime

!!! info "New in `0.2.0`"

    Runtime was split into several artifacts, allowing small API surfaces to be included.

    Split artifacts share the same version numbers.

![Maven Central Version](https://img.shields.io/maven-central/v/io.github.shadowrz.hanekokoro.framework/runtime)

=== "Kotlin DSL (`build.gradle.kts`)"

    ``` kotlin
    dependencies {
        implmentation("io.github.shadowrz.hanekokoro.framework:runtime:$version")
    }
    ```

=== "Groovy (`build.gradle`)"

    ```groovy
    dependencies {
        implmentation "io.github.shadowrz.hanekokoro.framework:runtime:$version"
    }
    ```

=== "Kotlin Multiplatform (Kotlin DSL)"

    ```kotlin
    kotlin {
        sourceSets {
            commonMain.dependencies {
                implmentation("io.github.shadowrz.hanekokoro.framework:runtime:$version")
            }
        }
    }
    ```

## Annotations

![Maven Central Version](https://img.shields.io/maven-central/v/io.github.shadowrz.hanekokoro.framework/annotations)

=== "Kotlin DSL (`build.gradle.kts`)"

    ``` kotlin
    dependencies {
        implmentation("io.github.shadowrz.hanekokoro.framework:annotations:$version")
    }
    ```

=== "Groovy (`build.gradle`)"

    ```groovy
    dependencies {
        implmentation "io.github.shadowrz.hanekokoro.framework:annotations:$version"
    }
    ```

=== "Kotlin Multiplatform (Kotlin DSL)"

    ```kotlin
    kotlin {
        sourceSets {
            commonMain.dependencies {
                implmentation("io.github.shadowrz.hanekokoro.framework:annotations:$version")
            }
        }
    }
    ```

## Codegen

KSP based Code Generation.

![Maven Central Version](https://img.shields.io/maven-central/v/io.github.shadowrz.hanekokoro.framework/annotations)

!!! warning

    Make sure you already setup KSP in your project. See [KSP quickstart](https://kotlinlang.org/docs/ksp-quickstart.html).

!!! note

    Due to KSP limitations, generated code only lives in platform specific source set.
