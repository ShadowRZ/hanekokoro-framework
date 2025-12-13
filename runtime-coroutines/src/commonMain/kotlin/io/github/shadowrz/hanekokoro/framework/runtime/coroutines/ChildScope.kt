package io.github.shadowrz.hanekokoro.framework.runtime.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.coroutineScope(context: CoroutineContext = EmptyCoroutineContext): CoroutineScope {
    val job = coroutineContext.job

    return CoroutineScope(context + Job(parent = job))
}

fun CoroutineScope.supervisorScope(context: CoroutineContext = EmptyCoroutineContext): CoroutineScope {
    val job = coroutineContext.job

    return CoroutineScope(context + SupervisorJob(parent = job))
}
