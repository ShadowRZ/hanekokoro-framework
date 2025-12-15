package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

@InternalHanekokoroApi
fun <C : Component> Component.childComponent(
    context: ComponentContext,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = this.context).apply {
            componentContext = context
        },
    )

@InternalHanekokoroApi
fun <C : Component> component(
    context: ComponentContext,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = null).apply {
            componentContext = context
        },
    )
