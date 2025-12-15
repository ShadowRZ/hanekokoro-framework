package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

@InternalHanekokoroApi
fun <C : Component> Component.childComponent(
    context: ComponentContext,
    onBackPressedCallback: OnBackPressedCallback? = null,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = this.context).apply {
            componentContext = context
            this.onBackPressedCallback = onBackPressedCallback
        },
    )

@InternalHanekokoroApi
fun <C : Component> component(
    context: ComponentContext,
    onBackPressedCallback: OnBackPressedCallback? = null,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = null).apply {
            componentContext = context
            this.onBackPressedCallback = onBackPressedCallback
        },
    )

fun Component.onBackPressed() = this.context.onBackPressedCallback?.onBackPressed()
