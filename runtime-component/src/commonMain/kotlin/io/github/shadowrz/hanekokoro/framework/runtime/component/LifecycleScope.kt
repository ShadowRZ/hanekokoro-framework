package io.github.shadowrz.hanekokoro.framework.runtime.component

import com.arkivanov.essenty.lifecycle.coroutines.withLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlin.concurrent.Volatile

/**
 * [CoroutineScope] tied to this [Component]'s lifecycle.
 *
 * This scope will be cancelled when the [Component] is destroyed.
 *
 * This scope is bound to [Dispatchers.Main.immediate][kotlinx.coroutines.MainCoroutineDispatcher.immediate],
 * falling back to [Dispatchers.Main][kotlinx.coroutines.Dispatchers.Main] if immediate dispatching is not avaliable.
 */
public val Component.lifecycleScope: CoroutineScope
    get() = this.context.tag()
        ?: CoroutineScope(Dispatchers.Main.immediateOrFallback + SupervisorJob()).withLifecycle(this.lifecycle).also {
            this.context.putTag(it)
        }

@Volatile
private var isImmediateSupported: Boolean = true

internal val MainCoroutineDispatcher.immediateOrFallback: MainCoroutineDispatcher
    get() {
        if (isImmediateSupported) {
            try {
                return immediate
            } catch (_: UnsupportedOperationException) {
            } catch (_: NotImplementedError) {
            }

            isImmediateSupported = false
        }

        return this
    }
