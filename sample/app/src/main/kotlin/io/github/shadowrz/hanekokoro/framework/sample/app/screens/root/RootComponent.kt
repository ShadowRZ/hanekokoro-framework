package io.github.shadowrz.hanekokoro.framework.sample.app.screens.root

import com.arkivanov.decompose.ComponentContext
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
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter.CounterComponent
import kotlinx.serialization.Serializable

@AssistedInject
@HanekokoroInject(AppScope::class)
class RootComponent(
    @Assisted context: HanekokoroContext,
    @Assisted plugins: List<Plugin> = emptyList(),
) : Component(
        context = context,
        plugins = plugins,
    ) {
    internal val navigation: StackNavigation<NavTarget> = StackNavigation()

    internal val childStack = childStack(
        source = navigation,
        serializer = NavTarget.serializer(),
        initialConfiguration = NavTarget.Root,
        handleBackButton = true,
        childFactory = ::resolve,
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

    private fun resolve(
        navTarget: NavTarget,
        context: ComponentContext,
    ): Resolved =
        when (navTarget) {
            NavTarget.Root -> Resolved.Root
            NavTarget.Counter -> Resolved.HasComponent(childComponent<CounterComponent>(context = context))
        }

    internal fun onNavTarget(navTarget: NavTarget) {
        navigation.pushNew(navTarget)
    }

    override fun onNavigateUp(onComplete: (Boolean) -> Unit) {
        navigation.pop(onComplete)
    }
}
