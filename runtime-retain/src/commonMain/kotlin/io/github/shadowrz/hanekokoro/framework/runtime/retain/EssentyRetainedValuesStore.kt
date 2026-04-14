package io.github.shadowrz.hanekokoro.framework.runtime.retain

import androidx.compose.runtime.retain.ManagedRetainedValuesStore
import androidx.compose.runtime.retain.RetainedValuesStore
import androidx.compose.runtime.retain.RetainedValuesStoreRegistry
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate

private class EssentyRetainedValuesStore(
    private val retainedValuesStore: ManagedRetainedValuesStore = ManagedRetainedValuesStore(),
) : RetainedValuesStore by retainedValuesStore,
    InstanceKeeper.Instance {
    override fun onDestroy() {
        retainedValuesStore.dispose()
    }
}

private class EssentyRetainedValuesStoreRegistry : InstanceKeeper.Instance {
    val registry = RetainedValuesStoreRegistry()

    override fun onDestroy() {
        registry.dispose()
    }
}

/**
 * Creates a [RetainedValuesStore] owned to the receiver [InstanceKeeperOwner].
 */
public fun InstanceKeeperOwner.retainedValuesStore(): RetainedValuesStore =
    instanceKeeper.getOrCreate {
        EssentyRetainedValuesStore()
    }

/**
 * Creates a [RetainedValuesStoreRegistry] owned to the receiver [InstanceKeeperOwner].
 */
public fun InstanceKeeperOwner.retainedValuesStoreRegistry(): RetainedValuesStoreRegistry =
    instanceKeeper.getOrCreate {
        EssentyRetainedValuesStoreRegistry()
    }.registry
