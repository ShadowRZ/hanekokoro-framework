package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

internal var HanekokoroContext.componentContext: ComponentContext
    get() = checkNotNull(tag())
    set(value) = putTag(value)
