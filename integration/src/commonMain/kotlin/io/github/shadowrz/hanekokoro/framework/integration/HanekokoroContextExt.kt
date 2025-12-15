package io.github.shadowrz.hanekokoro.framework.integration

import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

internal var HanekokoroContext.hanekokoroApp: HanekokoroApp
    get() = checkNotNull(tag())
    set(value) = putTag(value)
