package io.github.shadowrz.hanekokoro.framework.runtime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component

@Stable
fun interface UI {
    @Composable
    fun Content(modifier: Modifier)

    fun interface Factory<C : Component> {
        fun create(component: C): UI?
    }
}
