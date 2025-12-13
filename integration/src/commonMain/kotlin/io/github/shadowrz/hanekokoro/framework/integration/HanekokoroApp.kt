package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.childComponent
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.Renderer
import kotlin.reflect.KClass

@Immutable
class HanekokoroApp private constructor(
    builder: Builder,
) {
    private val componentFactories: Map<KClass<out Component>, Component.Factory<*>> = builder.componentFactories
    private val renderers: Map<KClass<out Component>, Renderer<*>> = builder.renderers

    inline fun <reified C : Component> component(
        context: ComponentContext,
        parent: Component,
        plugins: List<Plugin> = emptyList(),
    ): C =
        component(
            klass = C::class,
            context = context,
            parent = parent,
            plugins = plugins,
        )

    @Suppress("UNCHECKED_CAST")
    fun <C : Component> component(
        klass: KClass<C>,
        context: ComponentContext,
        parent: Component,
        plugins: List<Plugin> = emptyList(),
    ): C {
        val factory = requireNotNull(componentFactories[klass])
        return parent.childComponent(context = context) {
            factory.create(it, plugins = plugins) as C
        }
    }

    inline fun <reified C : Component> component(
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
    ): C =
        component(
            klass = C::class,
            context = context,
            plugins = plugins,
        )

    @Suppress("UNCHECKED_CAST")
    fun <C : Component> component(
        klass: KClass<C>,
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
    ): C {
        val factory = requireNotNull(componentFactories[klass])
        return factory.create(
            context = HanekokoroContext(context = context),
            plugins = plugins,
        ) as C
    }

    inline fun <reified C : Component> renderer(): Renderer<C> = renderer(C::class)

    @Suppress("UNCHECKED_CAST")
    fun <C : Component> renderer(klass: KClass<C>): Renderer<C> {
        val renderer = requireNotNull(renderers[klass])
        return renderer as Renderer<C>
    }

    class Builder {
        val componentFactories: MutableMap<KClass<out Component>, Component.Factory<*>> = mutableMapOf()
        val renderers: MutableMap<KClass<out Component>, Renderer<*>> = mutableMapOf()

        fun addComponentFactories(factories: Map<KClass<out Component>, Component.Factory<*>>) {
            componentFactories += factories
        }

        fun addRenderers(renderers: Map<KClass<out Component>, Renderer<*>>) {
            this.renderers += renderers
        }

        fun build(): HanekokoroApp = HanekokoroApp(this)
    }
}
