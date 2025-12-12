package io.github.shadowrz.hanekokoro.framework.runtime

import androidx.compose.runtime.staticCompositionLocalOf

val LocalHanekokoroApp = staticCompositionLocalOf<HanekokoroApp> {
    error("No LocalHanekokoroApp provided!")
}
