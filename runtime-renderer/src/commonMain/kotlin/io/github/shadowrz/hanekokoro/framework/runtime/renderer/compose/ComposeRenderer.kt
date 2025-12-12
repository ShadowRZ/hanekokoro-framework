package io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.Renderer

@Stable
abstract class ComposeRenderer<C : Component> : Renderer<C> {
    @Deprecated(
        message = "Do not call render() directly",
        level = DeprecationLevel.HIDDEN,
    )
    override fun render(component: C) {
        error("Do not call render() directly")
    }

    @Suppress("ComposableNaming")
    @Composable
    fun renderCompose(
        component: C,
        modifier: Modifier = Modifier,
    ) {
        Content(component = component, modifier = modifier)
    }

    @Suppress("ComposableNaming")
    @Composable
    protected abstract fun Content(
        component: C,
        modifier: Modifier = Modifier,
    )
}
