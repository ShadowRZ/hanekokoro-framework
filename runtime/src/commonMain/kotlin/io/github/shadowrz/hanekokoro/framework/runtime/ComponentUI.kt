package io.github.shadowrz.hanekokoro.framework.runtime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun interface ComponentUI<C : GenericComponent<*>> {
    @Composable
    fun Content(
        component: C,
        modifier: Modifier,
    )
}
