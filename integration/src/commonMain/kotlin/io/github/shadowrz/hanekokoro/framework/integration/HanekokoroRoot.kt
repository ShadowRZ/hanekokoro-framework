package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

@Composable
fun <C : Component> HanekokoroRoot(
    hanekokoroApp: HanekokoroApp,
    context: DefaultComponentContext,
    modifier: Modifier = Modifier,
    backDispatcher: BackDispatcher? = null,
    factory: (HanekokoroContext) -> C,
) = HanekokoroRoot(
    hanekokoroApp = hanekokoroApp,
    context = context,
    modifier = modifier,
    onBackPressedCallback = backDispatcher?.asOnBackPressedCallback(),
    factory = factory,
)

@OptIn(InternalHanekokoroApi::class)
@Composable
internal fun <C : Component> HanekokoroRoot(
    hanekokoroApp: HanekokoroApp,
    context: DefaultComponentContext,
    modifier: Modifier = Modifier,
    onBackPressedCallback: OnBackPressedCallback? = null,
    factory: (HanekokoroContext) -> C,
) = ProvideHanekokoroApp(hanekokoroApp) {
    val component = component(
        context = context,
        factory = factory,
        onBackPressedCallback = onBackPressedCallback,
    ).apply {
        this.context.hanekokoroApp = hanekokoroApp
    }
    HanekokoroContent(component = component, modifier = modifier)
}

internal fun BackDispatcher.asOnBackPressedCallback(): OnBackPressedCallback = OnBackPressedCallback { back() }
