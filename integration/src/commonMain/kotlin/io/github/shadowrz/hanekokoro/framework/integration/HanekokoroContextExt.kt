package io.github.shadowrz.hanekokoro.framework.integration

import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

var HanekokoroContext.hanekokoroApp: HanekokoroApp
    get() = checkNotNull(tag() ?: parent?.tag())
    set(value) = putTag(value)
