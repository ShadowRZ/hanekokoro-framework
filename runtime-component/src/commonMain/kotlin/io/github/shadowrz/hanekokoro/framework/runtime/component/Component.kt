package io.github.shadowrz.hanekokoro.framework.runtime.component

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnNavigateUpCallbackOwner
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner

@Stable
public open class Component(
    public val context: HanekokoroContext,
    override val plugins: List<Plugin> = emptyList(),
) : ComponentContext by context.componentContext,
    PluginsOwner,
    OnNavigateUpCallbackOwner {
    public val parent: Component? = context.parentComponent

    public fun navigateUp() {
        parent?.onNavigateUp {
            if (!it) this.backDispatcher?.back()
        }
    }

    override fun onNavigateUp(onComplete: (Boolean) -> Unit) {
        parent?.onNavigateUp(onComplete)
    }

    public fun interface Factory<out C : Component> {
        public fun create(
            context: HanekokoroContext,
            plugins: List<Plugin>,
        ): C
    }
}
