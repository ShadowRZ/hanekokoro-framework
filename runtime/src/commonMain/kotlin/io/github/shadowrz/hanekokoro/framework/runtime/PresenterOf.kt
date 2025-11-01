package io.github.shadowrz.hanekokoro.framework.runtime

import androidx.compose.runtime.Composable

inline fun <S> presenterOf(crossinline body: @Composable () -> S): Presenter<S> =
    object : Presenter<S> {
        @Composable
        override fun present(): S = body()
    }
