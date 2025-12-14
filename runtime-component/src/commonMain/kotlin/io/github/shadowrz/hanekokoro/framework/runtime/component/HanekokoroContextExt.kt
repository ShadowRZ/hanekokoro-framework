package io.github.shadowrz.hanekokoro.framework.runtime.component

import androidx.annotation.RestrictTo
import com.arkivanov.decompose.ComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

var HanekokoroContext.componentContext: ComponentContext
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    get() = checkNotNull(tag())

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    set(value) = putTag(value)
