package io.github.shadowrz.hanekokoro.framework.runtime.component

import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.NavModel
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.Resolver
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

public abstract class ParentComponent<NavTarget : Any, Resolved : Any>(
    context: HanekokoroContext,
    plugins: List<Plugin> = emptyList(),
) : Component(
        context = context,
        plugins = plugins,
    ),
    Resolver<NavTarget, Resolved> {
    public abstract val navModel: NavModel<*>

    override fun onNavigateUp(onComplete: (Boolean) -> Unit) {
        navModel.onNavigateUp(onComplete)
    }
}
