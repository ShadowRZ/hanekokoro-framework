package io.github.shadowrz.hanekokoro.framework.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.LocalRetainedValuesStore
import androidx.compose.runtime.retain.ManagedRetainedValuesStore
import androidx.compose.runtime.retain.RetainedValuesStore
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose.ComposeRenderer
import androidx.lifecycle.Lifecycle as JetpackLifecycle
import androidx.lifecycle.LifecycleOwner as JetpackLifecycleOwner
import androidx.lifecycle.LifecycleRegistry as JetpackLifecycleRegistry
import com.arkivanov.essenty.lifecycle.Lifecycle as EssentyLifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner as EssentyLifecycleOwner

@Composable
public fun <C : Component> HanekokoroContent(
    component: C,
    modifier: Modifier = Modifier,
    hanekokoroApp: HanekokoroApp = requireNotNull(LocalHanekokoroApp.current),
) {
    val renderer = requireNotNull(hanekokoroApp.renderer(component::class) as? ComposeRenderer<C>) {
        "The component ${component::class} doesn't have a Compose renderer!"
    }

    val retainedValuesStore = remember(component) { component.retainedValuesStore() }

    CompositionLocalProvider(
        LocalLifecycleOwner provides component.jetpackLifecycleOwner,
        LocalRetainedValuesStore provides retainedValuesStore,
    ) {
        renderer.renderCompose(component, modifier = modifier)
    }
}

private val Component.jetpackLifecycleOwner: JetpackLifecycleOwner
    get() = this.context.tag() ?: DelegateLifecycleOwner(this).also { this.context.putTag(it) }

private class DelegateLifecycleOwner(
    delegate: EssentyLifecycleOwner,
) : JetpackLifecycleOwner {
    private val registry = JetpackLifecycleRegistry(this)

    private val callbacks = Callbacks()

    override val lifecycle: JetpackLifecycle = registry

    init {
        delegate.lifecycle.subscribe(callbacks)
    }

    inner class Callbacks : EssentyLifecycle.Callbacks {
        override fun onCreate() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_CREATE)
        }

        override fun onStart() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_START)
        }

        override fun onResume() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_RESUME)
        }

        override fun onPause() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_PAUSE)
        }

        override fun onStop() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_STOP)
        }

        override fun onDestroy() {
            registry.handleLifecycleEvent(JetpackLifecycle.Event.ON_DESTROY)
        }
    }
}

private class EssentyRetainedValuesStore(
    private val retainedValuesStore: ManagedRetainedValuesStore = ManagedRetainedValuesStore(),
) : RetainedValuesStore by retainedValuesStore,
    InstanceKeeper.Instance {
    override fun onDestroy() {
        retainedValuesStore.dispose()
    }
}

/**
 * Creates a [RetainedValuesStore] owned to the receiver [InstanceKeeperOwner].
 */
private fun InstanceKeeperOwner.retainedValuesStore(): RetainedValuesStore =
    instanceKeeper.getOrCreate {
        EssentyRetainedValuesStore()
    }
