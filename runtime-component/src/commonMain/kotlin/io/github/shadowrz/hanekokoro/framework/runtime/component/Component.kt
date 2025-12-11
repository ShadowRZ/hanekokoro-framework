package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.core.ParentOwner
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner

open class Component(
    context: ComponentContext,
    override val plugins: List<Plugin> = emptyList(),
    override val parent: Component? = null,
) : ComponentContext by context,
    ParentOwner<Component>,
    PluginsOwner {
    fun interface Factory<out C : Component> {
        fun create(
            context: ComponentContext,
            plugins: List<Plugin>,
            parent: Component?,
        ): C
    }
}
