package io.github.shadowrz.hanekokoro.framework.runtime.coroutines

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

private class RetainedCoroutineScope(
    context: CoroutineContext,
) : InstanceKeeper.Instance,
    CoroutineScope by CoroutineScope(context = context) {
    override fun onDestroy() {
        this.cancel()
    }
}

/**
 * Returns a retained [CoroutineScope] bound to the receiver [InstanceKeeperOwner]
 */
public fun InstanceKeeperOwner.retainedScope(context: CoroutineContext = Dispatchers.Main + SupervisorJob()): CoroutineScope =
    instanceKeeper.getOrCreate {
        RetainedCoroutineScope(context = context)
    }
