package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.component.component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

@OptIn(InternalHanekokoroApi::class)
@Composable
fun <C : Component> HanekokoroRoot(
    hanekokoroApp: HanekokoroApp,
    context: ComponentContext,
    modifier: Modifier = Modifier,
    factory: (HanekokoroContext) -> C,
) = ProvideHanekokoroApp(hanekokoroApp) {
    val component = component(
        context = context,
        factory = { factory(it.apply { this.hanekokoroApp = hanekokoroApp }) },
        backDispatcher = context.backHandler as? BackDispatcher,
    )
    HanekokoroContent(component = component, modifier = modifier)
}
