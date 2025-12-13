package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

abstract class HanekokoroAppComponent(
    context: HanekokoroContext,
    plugins: List<Plugin> = emptyList(),
) : Component(
        context = context,
        plugins = plugins,
    ) {
    constructor(
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
    ) : this(context = HanekokoroContext(context = context), plugins = plugins)

    abstract val hanekokoroApp: HanekokoroApp

    init {
        super.context.hanekokoroApp = hanekokoroApp
    }
}
