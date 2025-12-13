package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable

@Composable
@NonRestartableComposable
fun ProvideHanekokoroApp(
    hanekokoroApp: HanekokoroApp,
    content: @Composable () -> Unit,
) = CompositionLocalProvider(
    LocalHanekokoroApp provides hanekokoroApp,
    content = content,
)
