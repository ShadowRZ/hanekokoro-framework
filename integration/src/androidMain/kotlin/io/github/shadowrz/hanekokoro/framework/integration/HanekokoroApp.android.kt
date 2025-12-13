package io.github.shadowrz.hanekokoro.framework.integration

import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.arkivanov.decompose.defaultComponentContext
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin
import kotlin.reflect.KClass

@Composable
@NonRestartableComposable
inline fun <T, reified C : Component> T.HanekokoroApp(
    hanekokoroApp: HanekokoroApp,
    modifier: Modifier = Modifier,
    plugins: List<Plugin> = emptyList(),
    discardSavedState: Boolean = false,
    noinline isStateSavingAllowed: () -> Boolean = { true },
) where T : SavedStateRegistryOwner, T : OnBackPressedDispatcherOwner, T : ViewModelStoreOwner, T : LifecycleOwner =
    HanekokoroApp(
        hanekokoroApp = hanekokoroApp,
        discardSavedState = discardSavedState,
        isStateSavingAllowed = isStateSavingAllowed,
        plugins = plugins,
        klass = C::class,
        modifier = modifier,
    )

@Composable
fun <T, C : Component> T.HanekokoroApp(
    hanekokoroApp: HanekokoroApp,
    klass: KClass<C>,
    modifier: Modifier = Modifier,
    plugins: List<Plugin> = emptyList(),
    discardSavedState: Boolean = false,
    isStateSavingAllowed: () -> Boolean = { true },
) where T : SavedStateRegistryOwner, T : OnBackPressedDispatcherOwner, T : ViewModelStoreOwner, T : LifecycleOwner =
    ProvideHanekokoroApp(hanekokoroApp) {
        val context = defaultComponentContext(
            discardSavedState = discardSavedState,
            isStateSavingAllowed = isStateSavingAllowed,
        )
        val component = hanekokoroApp.component(klass = klass, context = context, plugins = plugins)
        HanekokoroContent(component = component, modifier = modifier)
    }
