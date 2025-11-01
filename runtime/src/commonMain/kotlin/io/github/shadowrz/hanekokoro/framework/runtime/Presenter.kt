package io.github.shadowrz.hanekokoro.framework.runtime

import androidx.compose.runtime.Composable

fun interface Presenter<S> {
    @Composable
    fun present(): S
}
