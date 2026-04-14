package io.github.shadowrz.hanekokoro.framework.runtime.renderer

import io.github.shadowrz.hanekokoro.framework.runtime.component.Component

public fun interface Renderer<C : Component> {
    public fun render(component: C)
}
