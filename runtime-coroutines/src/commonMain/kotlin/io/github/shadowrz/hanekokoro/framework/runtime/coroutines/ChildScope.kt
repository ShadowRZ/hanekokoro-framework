package io.github.shadowrz.hanekokoro.framework.runtime.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Creates a child [CoroutineScope], optionally with additional contexts.
 *
 * The returned scope inherits its [coroutineContext][CoroutineScope.coroutineContext] from the receiver scope,
 * using the [Job] from that context as the parent for a new [Job].
 *
 * When any child coroutine in this scope fails,
 * this scope fails, cancelling all the other children (for a different behavior, see [supervisorScope]).
 *
 * @param context Additional contexts for the new scope, such as [kotlinx.coroutines.CoroutineDispatcher]
 */
fun CoroutineScope.coroutineScope(context: CoroutineContext = EmptyCoroutineContext): CoroutineScope {
    val job = coroutineContext.job

    return CoroutineScope(coroutineContext + context + Job(parent = job))
}

/**
 * Creates a [CoroutineScope] with [SupervisorJob], optionally with additional contexts.
 *
 * The returned scope inherits its [coroutineContext][CoroutineScope.coroutineContext] from the receiver scope,
 * using the [Job] from that context as the parent for the new [SupervisorJob].
 *
 * Unlike [coroutineScope], a failure of a child does not cause this scope to fail and does not affect its other children,
 * so a custom policy for handling failures of its children can be implemented. See [SupervisorJob] for additional details.
 *
 * @param context Additional contexts for the new scope, such as [kotlinx.coroutines.CoroutineDispatcher]
 */
fun CoroutineScope.supervisorScope(context: CoroutineContext = EmptyCoroutineContext): CoroutineScope {
    val job = coroutineContext.job

    return CoroutineScope(coroutineContext + context + SupervisorJob(parent = job))
}
