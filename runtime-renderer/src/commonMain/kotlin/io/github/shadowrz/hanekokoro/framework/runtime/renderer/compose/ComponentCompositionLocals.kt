package io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.LocalRetainedValuesStore
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.lifecycle.lifecycleOwner
import io.github.shadowrz.hanekokoro.framework.runtime.retain.retainedValuesStore

@Composable
internal fun <C : Component> ComponentCompositionLocals(
    component: C,
    content: @Composable () -> Unit,
) {
    val lifecycleOwner = remember(component) {
        component.lifecycleOwner()
    }
    val retainedValuesStore = remember(component) {
        component.retainedValuesStore()
    }

    CompositionLocalProvider(
        LocalLifecycleOwner provides lifecycleOwner,
        LocalRetainedValuesStore provides retainedValuesStore,
        content = content,
    )
}
