package io.github.shadowrz.hanekokoro.framework.runtime.renderer

import io.github.shadowrz.hanekokoro.framework.runtime.component.Component

fun interface Renderer<C : Component> {
    fun render(component: C)
}
