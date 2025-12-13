package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component

inline fun <reified C : Component> Component.childComponent(context: ComponentContext) =
    this.context.hanekokoroApp.component<C>(context = context)
