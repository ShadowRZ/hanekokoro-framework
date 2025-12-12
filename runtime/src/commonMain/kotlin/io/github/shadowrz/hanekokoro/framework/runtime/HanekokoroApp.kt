package io.github.shadowrz.hanekokoro.framework.runtime

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import kotlin.reflect.KClass

@Immutable
class HanekokoroApp private constructor(
    builder: Builder,
) {
    private val componentFactories: Map<KClass<out Component>, Component.Factory<*>> = builder.componentFactories

    inline fun <reified C : Component> component(
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
        parent: Component? = null,
    ): C? =
        component(
            klass = C::class,
            context = context,
            plugins = plugins,
            parent = parent,
        )

    @Suppress("UNCHECKED_CAST")
    fun <C : Component> component(
        klass: KClass<C>,
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
        parent: Component? = null,
    ): C? {
        val factory = componentFactories[klass]
        return factory?.create(context = context, plugins = plugins, parent = parent) as C?
    }

    class Builder {
        val componentFactories: MutableMap<KClass<out Component>, Component.Factory<*>> = mutableMapOf()

        fun addComponentFactories(factories: Map<KClass<out Component>, Component.Factory<*>>) {
            componentFactories += factories
        }

        fun build(): HanekokoroApp = HanekokoroApp(this)
    }
}
