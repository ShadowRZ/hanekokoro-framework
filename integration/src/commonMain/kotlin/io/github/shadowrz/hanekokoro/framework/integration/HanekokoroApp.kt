package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.childComponent
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import kotlin.reflect.KClass

@Immutable
class HanekokoroApp private constructor(
    builder: Builder,
) {
    private val componentFactories: Map<KClass<out Component>, Component.Factory<*>> = builder.componentFactories

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

    class Builder {
        val componentFactories: MutableMap<KClass<out Component>, Component.Factory<*>> = mutableMapOf()

        fun addComponentFactories(factories: Map<KClass<out Component>, Component.Factory<*>>) {
            componentFactories += factories
        }

        fun build(): HanekokoroApp = HanekokoroApp(this)
    }
}
