package io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.LocalRetainedValuesStore
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.lifecycle.lifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.Renderer

@Stable
public abstract class ComposeRenderer<C : Component> : Renderer<C> {
    @Deprecated(
        message = "Do not call render() directly",
        level = DeprecationLevel.HIDDEN,
    )
    final override fun render(component: C) {
        error("Do not call render() directly")
    }

    @Suppress("ComposableNaming")
    @Composable
    public fun renderCompose(
        component: C,
        modifier: Modifier = Modifier,
    ): Unit =
        ComponentCompositionLocals(component = component) {
            Content(component = component, modifier = modifier)
        }

    @Suppress("ComposableNaming")
    @Composable
    protected abstract fun Content(
        component: C,
        modifier: Modifier = Modifier,
    )
}
