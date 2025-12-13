package io.github.shadowrz.hanekokoro.framework.runtime.presenter

import androidx.compose.runtime.Composable
import io.github.shadowrz.hanekokoro.framework.markers.HanekokoroState

fun interface Presenter<S : HanekokoroState> {
    @Composable
    fun present(): S
}
