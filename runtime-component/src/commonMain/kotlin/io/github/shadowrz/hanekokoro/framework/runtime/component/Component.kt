package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner

open class Component(
    val context: HanekokoroContext,
    override val plugins: List<Plugin> = emptyList(),
) : ComponentContext by context.componentContext,
    PluginsOwner {
    fun interface Factory<out C : Component> {
        fun create(
            context: HanekokoroContext,
            plugins: List<Plugin>,
        ): C
    }
}
