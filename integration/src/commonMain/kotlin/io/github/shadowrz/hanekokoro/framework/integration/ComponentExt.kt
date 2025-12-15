package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

inline fun <reified C : Component> Component.childComponent(
    context: ComponentContext,
    plugins: List<Plugin> = emptyList(),
    hanekokoroApp: HanekokoroApp? = null,
) = (hanekokoroApp ?: this.hanekokoroApp).component<C>(context = context, parent = this, plugins = plugins)

val Component.hanekokoroApp: HanekokoroApp
    get() = this.context.hanekokoroApp
