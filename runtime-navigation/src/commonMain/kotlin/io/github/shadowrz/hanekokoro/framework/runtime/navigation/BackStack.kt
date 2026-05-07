package io.github.shadowrz.hanekokoro.framework.runtime.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.StackNavigator
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.reflect.KClass

public class BackStack<NavTarget : Any, Resolved : Any> internal constructor(
    private val navigation: StackNavigation<NavTarget>,
    override val children: Value<ChildStack<NavTarget, Resolved>>,
) : NavModel<ChildStack<NavTarget, Resolved>>,
    StackNavigator<NavTarget> by navigation {
    override fun onNavigateUp(onComplete: (Boolean) -> Unit) {
        navigation.pop(onComplete = onComplete)
    }
}
