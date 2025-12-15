package io.github.shadowrz.hanekokoro.framework.integration

import androidx.activity.OnBackPressedDispatcher
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

fun OnBackPressedDispatcher.asOnBackPressedCallback() = OnBackPressedCallback { onBackPressed() }
