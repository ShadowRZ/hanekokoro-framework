package io.github.shadowrz.hanekokoro.framework.integration

import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

var HanekokoroContext.hanekokoroApp: HanekokoroApp
    get() = checkNotNull(tag())
    set(value) = putTag(value)
