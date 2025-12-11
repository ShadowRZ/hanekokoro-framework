package io.github.shadowrz.hanekokoro.framework.runtime.presenter

import androidx.compose.runtime.Composable

fun interface Presenter<S> {
    @Composable
    fun present(): S
}
