package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.navigation.OnBackPressedCallback

@OptIn(InternalHanekokoroApi::class)
@Composable
fun <C : Component> HanekokoroRoot(
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
