package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

abstract class HanekokoroAppComponent(
    context: ComponentContext,
    plugins: List<Plugin> = emptyList(),
) : Component(
        context = HanekokoroContext(context = context),
        plugins = plugins,
    ) {
    abstract val hanekokoroApp: HanekokoroApp

    init {
        super.context.hanekokoroApp = hanekokoroApp
    }
}
