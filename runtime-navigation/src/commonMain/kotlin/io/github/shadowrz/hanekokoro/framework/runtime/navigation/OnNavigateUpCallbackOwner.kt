package io.github.shadowrz.hanekokoro.framework.runtime.navigation

fun interface OnNavigateUpCallbackOwner {
    fun onNavigateUp(onComplete: (Boolean) -> Unit)
}
