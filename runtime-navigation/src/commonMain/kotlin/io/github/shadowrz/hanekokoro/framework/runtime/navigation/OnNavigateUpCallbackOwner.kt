package io.github.shadowrz.hanekokoro.framework.runtime.navigation

public fun interface OnNavigateUpCallbackOwner {
    public fun onNavigateUp(onComplete: (Boolean) -> Unit)
}
