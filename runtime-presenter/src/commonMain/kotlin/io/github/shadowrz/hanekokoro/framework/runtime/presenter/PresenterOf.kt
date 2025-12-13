package io.github.shadowrz.hanekokoro.framework.runtime.presenter

import androidx.compose.runtime.Composable
import io.github.shadowrz.hanekokoro.framework.markers.HanekokoroState

inline fun <S : HanekokoroState> presenterOf(crossinline body: @Composable () -> S): Presenter<S> = Presenter { body() }
