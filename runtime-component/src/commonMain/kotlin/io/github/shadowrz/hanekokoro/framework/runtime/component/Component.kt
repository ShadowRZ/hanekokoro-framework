package io.github.shadowrz.hanekokoro.framework.runtime.component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnNavigateUpCallbackOwner
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner

@Stable
open class Component(
    val context: HanekokoroContext,
    override val plugins: List<Plugin> = emptyList(),
) : ComponentContext by context.componentContext,
    PluginsOwner,
    OnNavigateUpCallbackOwner {
    val parent = context.parentComponent

    fun navigateUp() {
        parent?.onNavigateUp {
            if (!it) context.backDispatcher?.back()
        }
    }

    override fun onNavigateUp(onComplete: (Boolean) -> Unit) {
        parent?.onNavigateUp(onComplete)
    }

    fun interface Factory<out C : Component> {
        fun create(
            context: HanekokoroContext,
            plugins: List<Plugin>,
        ): C
    }
}
