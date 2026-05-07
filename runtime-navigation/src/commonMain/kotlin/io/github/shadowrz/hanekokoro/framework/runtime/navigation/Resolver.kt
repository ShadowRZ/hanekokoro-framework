package io.github.shadowrz.hanekokoro.framework.runtime.navigation

import com.arkivanov.decompose.ComponentContext

public interface Resolver<NavTarget : Any, Resolved : Any> {
    public fun resolve(
        navTarget: NavTarget,
        context: ComponentContext,
    ): Resolved
}
