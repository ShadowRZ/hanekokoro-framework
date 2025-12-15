package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

internal var HanekokoroContext.componentContext: ComponentContext
    get() = checkNotNull(tag())
    set(value) = putTag(value)

internal var HanekokoroContext.onBackPressedCallback: OnBackPressedCallback?
    get() = tag() ?: parent?.tag()
    set(value) = putTag(value)
