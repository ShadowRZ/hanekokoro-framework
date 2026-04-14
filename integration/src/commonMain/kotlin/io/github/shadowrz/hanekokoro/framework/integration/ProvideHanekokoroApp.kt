package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable

@Composable
@NonRestartableComposable
public fun ProvideHanekokoroApp(
    hanekokoroApp: HanekokoroApp,
    content: @Composable () -> Unit,
): Unit =
    CompositionLocalProvider(
        LocalHanekokoroApp provides hanekokoroApp,
        content = content,
    )
