package io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedInject
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.context.HanekokoroContext
import io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

@AssistedInject
@HanekokoroInject.ContributesComponent(AppScope::class)
class CounterComponent(
    @Assisted context: HanekokoroContext,
    @Assisted plugins: List<Plugin>,
) : Component(
        context = context,
        plugins = plugins,
    ) {
    private val _counter: MutableValue<Int> = MutableValue(0)

    val counter: Value<Int> = _counter

    internal fun increment() {
        _counter.value += 1
    }

    internal fun decrement() {
        _counter.value -= 1
    }
}
