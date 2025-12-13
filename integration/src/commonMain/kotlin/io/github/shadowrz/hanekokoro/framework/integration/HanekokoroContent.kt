package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.lifecycle.lifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose.ComposeRenderer

@Composable
fun <C : Component> HanekokoroContent(
    component: C,
    modifier: Modifier = Modifier,
    hanekokoroApp: HanekokoroApp = requireNotNull(LocalHanekokoroApp.current),
) {
    val renderer = requireNotNull(hanekokoroApp.renderer(component::class) as? ComposeRenderer<C>) {
        "The component ${component::class} doesn't have a Compose renderer!"
    }

    val lifecycleOwner = remember(component) {
        component.lifecycleOwner()
    }

    CompositionLocalProvider(
        LocalLifecycleOwner provides lifecycleOwner,
    ) {
        renderer.renderCompose(component, modifier = modifier)
    }
}
