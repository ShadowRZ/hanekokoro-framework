package io.github.shadowrz.hanekokoro.framework.runtime.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.GenericComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.KSerializer

public fun <Ctx, C : Any, T : Any> Ctx.backStack(
    serializer: KSerializer<C>?,
    initialStack: () -> List<C>,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
): BackStack<C, T>
where Ctx : ComponentContext,
      Ctx : Resolver<C, T> {
    val navigation = StackNavigation<C>()

    val children = childStack(
        source = navigation,
        serializer = serializer,
        initialStack = initialStack,
        key = key,
        handleBackButton = handleBackButton,
        childFactory = ::resolve,
    )

    return BackStack(
        navigation = navigation,
        children = children,
    )
}

public fun <Ctx, C : Any, T : Any> Ctx.backStack(
    serializer: KSerializer<C>?,
    initialConfiguration: C,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
): BackStack<C, T>
    where Ctx : ComponentContext,
          Ctx : Resolver<C, T> =
    backStack(
        serializer = serializer,
        key = key,
        initialStack = { listOf(initialConfiguration) },
        handleBackButton = handleBackButton,
    )
