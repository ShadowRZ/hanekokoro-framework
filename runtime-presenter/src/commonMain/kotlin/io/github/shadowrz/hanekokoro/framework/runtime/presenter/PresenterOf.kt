package io.github.shadowrz.hanekokoro.framework.runtime.presenter

import androidx.compose.runtime.Composable

inline fun <S> presenterOf(crossinline body: @Composable () -> S): Presenter<S> = Presenter { body() }
