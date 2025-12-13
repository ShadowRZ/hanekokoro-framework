package io.github.shadowrz.hanekokoro.framework.integration

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.componentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

var HanekokoroContext.hanekokoroApp: HanekokoroApp
    get() = checkNotNull(tag() ?: parent?.tag())
    set(value) = putTag(value)

@Suppress("ktlint:standard:function-naming")
internal fun HanekokoroContext(context: ComponentContext) =
    HanekokoroContext(parent = null).apply {
        this.componentContext = context
    }
