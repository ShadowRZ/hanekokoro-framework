package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

abstract class HanekokoroAppComponent(
    val hanekokoroApp: HanekokoroApp,
    context: HanekokoroContext,
    plugins: List<Plugin> = emptyList(),
) : Component(
        context = context,
        plugins = plugins,
    ) {
    constructor(
        hanekokoroApp: HanekokoroApp,
        context: ComponentContext,
        plugins: List<Plugin> = emptyList(),
    ) : this(
        hanekokoroApp = hanekokoroApp,
        context = HanekokoroContext(context = context),
        plugins = plugins,
    )

    init {
        super.context.hanekokoroApp = hanekokoroApp
    }
}
