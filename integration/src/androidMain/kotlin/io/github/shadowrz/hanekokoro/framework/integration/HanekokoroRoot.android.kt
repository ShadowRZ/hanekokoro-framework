package io.github.shadowrz.hanekokoro.framework.integration

import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.arkivanov.decompose.defaultComponentContext
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext

@OptIn(InternalHanekokoroApi::class)
@Composable
@NonRestartableComposable
fun <T, C : Component> T.HanekokoroRoot(
    hanekokoroApp: HanekokoroApp,
    modifier: Modifier = Modifier,
    discardSavedState: Boolean = false,
    isStateSavingAllowed: () -> Boolean = { true },
    factory: (HanekokoroContext) -> C,
) where T : SavedStateRegistryOwner, T : OnBackPressedDispatcherOwner, T : ViewModelStoreOwner, T : LifecycleOwner =
    HanekokoroRoot(
        hanekokoroApp = hanekokoroApp,
        modifier = modifier,
        context = defaultComponentContext(
            discardSavedState = discardSavedState,
            isStateSavingAllowed = isStateSavingAllowed,
        ),
        onBackPressedCallback = onBackPressedDispatcher.asOnBackPressedCallback(),
        factory = factory,
    )
