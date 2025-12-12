package io.github.shadowrz.hanekokoro.framework.runtime.context

import com.arkivanov.decompose.ComponentContext

var HanekokoroContext.componentContext: ComponentContext
    get() = checkNotNull(tag())
    set(value) = putTag(value)
