package io.github.shadowrz.hanekokoro.framework.integration

import androidx.annotation.RestrictTo
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.componentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

var HanekokoroContext.hanekokoroApp: HanekokoroApp
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = checkNotNull(tag() ?: parent?.tag())

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    set(value) = putTag(value)

@Suppress("ktlint:standard:function-naming")
internal fun HanekokoroContext(context: ComponentContext) =
    HanekokoroContext(parent = null).apply {
        this.componentContext = context
    }
