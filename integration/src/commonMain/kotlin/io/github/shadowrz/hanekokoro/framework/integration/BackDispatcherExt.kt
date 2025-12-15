package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.essenty.backhandler.BackDispatcher
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

fun BackDispatcher.asOnBackPressedCallback(): OnBackPressedCallback = OnBackPressedCallback { back() }
