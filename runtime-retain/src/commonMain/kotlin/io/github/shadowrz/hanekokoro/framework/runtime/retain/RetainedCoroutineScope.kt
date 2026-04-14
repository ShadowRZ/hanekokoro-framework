package io.github.shadowrz.hanekokoro.framework.runtime.retain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.retain.RetainObserver
import androidx.compose.runtime.retain.retain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Return a [retained][retain] [CoroutineScope], using the optional [CoroutineContext] provided by [getContext].
 * [getContext] will only be called once and the same [CoroutineScope] instance will be retained.
 *
 * This scope will be [cancelled][CoroutineScope.cancel]
 * when it was [retired][RetainObserver.onRetired] in the composition. The
 * [CoroutineContext] returned by [getContext] may not contain a [Job] as this scope is considered
 * to be independent of the composition.
 *
 * The default dispatcher of this scope if one is not provided by the context returned by
 * [getContext] will be the default dispatcher of [CoroutineScope].
 *
 * This function will not throw if preconditions are not met, as composable functions do not yet
 * fully support exceptions. Instead the returned scope's [CoroutineScope.coroutineContext] will
 * contain a failed [Job] with the associated exception and will not be capable of launching child
 * jobs.
 */
@Composable
public inline fun retainCoroutineScope(
    crossinline getContext: @DisallowComposableCalls () -> CoroutineContext = {
        EmptyCoroutineContext
    },
): CoroutineScope = retain { createCompositionCoroutineScope(getContext()) }

@PublishedApi
internal fun createCompositionCoroutineScope(coroutineContext: CoroutineContext): CoroutineScope =
    if (coroutineContext[Job] != null) {
        CoroutineScope(
            Job().apply {
                completeExceptionally(
                    IllegalArgumentException(
                        "CoroutineContext supplied to " +
                            "retainCoroutineScope may not include a parent job",
                    ),
                )
            },
        )
    } else {
        RetainedCoroutineScope(context = coroutineContext)
    }

@Suppress("detekt:EmptyFunctionBlock")
private class RetainedCoroutineScope(
    context: CoroutineContext,
) : RetainObserver,
    CoroutineScope by CoroutineScope(context = context) {
    override fun onEnteredComposition() = Unit

    override fun onExitedComposition() = Unit

    override fun onRetained() = Unit

    override fun onRetired() {
        cancel()
    }

    override fun onUnused() {
        cancel()
    }
}
