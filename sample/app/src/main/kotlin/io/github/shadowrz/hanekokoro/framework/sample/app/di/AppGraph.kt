package io.github.shadowrz.hanekokoro.framework.sample.app.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provides
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import io.github.shadowrz.hanekokoro.framework.integration.HanekokoroApp
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.Renderer
import kotlin.reflect.KClass

@DependencyGraph(scope = AppScope::class)
interface AppGraph : MetroAppComponentProviders {
    @Multibinds val componentFactories: Map<KClass<out Component>, Component.Factory<*>>

    @Multibinds val renderers: Map<KClass<out Component>, Renderer<*>>

    @Provides
    fun providesHanekokoroApp(
        componentFactories: Map<KClass<out Component>, Component.Factory<*>>,
        renderers: Map<KClass<out Component>, Renderer<*>>,
    ): HanekokoroApp =
        HanekokoroApp.Builder()
            .addComponentFactories(componentFactories)
            .addRenderers(renderers)
            .build()
}
