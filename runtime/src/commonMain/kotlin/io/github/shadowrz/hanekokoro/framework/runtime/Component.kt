package io.github.shadowrz.hanekokoro.framework.runtime

import com.arkivanov.decompose.ComponentContext

abstract class Component(
    context: ComponentContext,
    override val plugins: List<Plugin>,
    override val parent: GenericComponent<*>?,
) : ComponentContext by context,
    GenericComponent<ComponentContext>
