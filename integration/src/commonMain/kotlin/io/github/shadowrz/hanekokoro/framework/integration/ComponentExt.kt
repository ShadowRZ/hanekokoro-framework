package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

inline fun <reified C : Component> Component.childComponent(
    context: ComponentContext,
    plugins: List<Plugin> = emptyList(),
    hanekokoroApp: HanekokoroApp? = null,
) = (hanekokoroApp ?: this.hanekokoroApp).component<C>(context = context, parent = this, plugins = plugins)

val Component.hanekokoroApp: HanekokoroApp
    get() = this.context.hanekokoroApp

@OptIn(InternalHanekokoroApi::class)
internal fun <C : Component> component(
    context: ComponentContext,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = null).apply {
            putTag<ComponentContext>(context)
        },
    )

@OptIn(InternalHanekokoroApi::class)
fun <C : Component> Component.childComponent(
    context: ComponentContext,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = this.context).apply {
            putTag<ComponentContext>(context)
            putTag<Component>(this@childComponent)
        },
    )
