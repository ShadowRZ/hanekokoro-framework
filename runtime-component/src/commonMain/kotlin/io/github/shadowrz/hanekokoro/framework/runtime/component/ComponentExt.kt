package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

@InternalHanekokoroApi
fun <C : Component> Component.childComponent(
    context: ComponentContext,
    backDispatcher: BackDispatcher? = null,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = this.context).apply {
            componentContext = context
            parentComponent = this@childComponent
            this.backDispatcher = backDispatcher
        },
    )

@InternalHanekokoroApi
fun <C : Component> component(
    context: ComponentContext,
    backDispatcher: BackDispatcher? = null,
    factory: (HanekokoroContext) -> C,
): C =
    factory(
        HanekokoroContext(parent = null).apply {
            componentContext = context
            this.backDispatcher = backDispatcher
        },
    )
