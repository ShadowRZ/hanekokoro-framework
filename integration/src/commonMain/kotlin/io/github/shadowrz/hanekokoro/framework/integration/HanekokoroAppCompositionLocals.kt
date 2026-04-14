package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

public val LocalHanekokoroApp: ProvidableCompositionLocal<HanekokoroApp?> =
    staticCompositionLocalOf<HanekokoroApp?> { null }
