package io.github.shadowrz.hanekokoro.framework.runtime.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

inline fun ui(crossinline body: @Composable (modifier: Modifier) -> Unit): UI = UI { modifier -> body(modifier) }
