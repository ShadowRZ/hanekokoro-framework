package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.ClassName

internal class Symbols(
    resolver: Resolver,
) {
    val modifier = resolver.loadKSType(Names.Modifier.canonicalName)
    val renderer = resolver.loadKSType(Names.Renderer.canonicalName)

    private fun Resolver.loadKSType(name: String): KSType = loadOptionalKSType(name) ?: error("Could not find $name in classpath")

    private fun Resolver.loadOptionalKSType(name: String?): KSType? {
        if (name == null) return null
        return getClassDeclarationByName(getKSNameFromString(name))?.asType(emptyList())
    }

    object Names {
        val Component = ClassName(Packages.COMPONENT, "Component")
        val ComponentFactory = ClassName(Packages.COMPONENT, "Component", "Factory")
        val ComponentKey = ClassName(Packages.COMPONENT, "ComponentKey")
        val ComposeRenderer = ClassName("io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose", "ComposeRenderer")
        val Renderer = ClassName("io.github.shadowrz.hanekokoro.framework.runtime.renderer", "Renderer")

        // This helps in KotlinPoet.
        val binding = ClassName(Packages.METRO, "binding")
        val Modifier = ClassName("androidx.compose.ui", "Modifier")
        val Composable = ClassName("androidx.compose.runtime", "Composable")
        val Plugin = ClassName("io.github.shadowrz.hanekokoro.framework.runtime.plugin", "Plugin")
    }

    object Packages {
        const val COMPONENT = "io.github.shadowrz.hanekokoro.framework.runtime.component"
        const val METRO = "dev.zacsweers.metro"
    }

    object Placeholders {
        const val CLASS_PLACEHOLDER = "%T::class"
    }
}
