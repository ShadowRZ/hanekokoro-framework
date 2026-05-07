package io.github.shadowrz.hanekokoro.framework.sample.app.screens.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedInject
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject
import io.github.shadowrz.hanekokoro.framework.integration.childComponent
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.ParentComponent
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.BackStack
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.NavModel
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.backStack
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter.CounterComponent
import kotlinx.serialization.Serializable

@AssistedInject
@HanekokoroInject(AppScope::class)
class RootComponent(
    @Assisted context: HanekokoroContext,
    @Assisted plugins: List<Plugin> = emptyList(),
) : ParentComponent<RootComponent.NavTarget, RootComponent.Resolved>(
        context = context,
        plugins = plugins,
    ) {
    override val navModel: BackStack<NavTarget, Resolved> = backStack(
        serializer = NavTarget.serializer(),
        initialConfiguration = NavTarget.Root,
    )

    @Serializable
    sealed interface NavTarget {
        @Serializable
        data object Root : NavTarget

        data object Counter : NavTarget
    }

    sealed interface Resolved {
        data object Root : Resolved

        data class HasComponent(
            val component: Component,
        ) : Resolved
    }

    override fun resolve(
        navTarget: NavTarget,
        context: ComponentContext,
    ): Resolved =
        when (navTarget) {
            NavTarget.Root -> Resolved.Root
            NavTarget.Counter -> Resolved.HasComponent(childComponent<CounterComponent>(context = context))
        }

    internal fun onNavTarget(navTarget: NavTarget) {
        navModel.pushNew(navTarget)
    }
}
