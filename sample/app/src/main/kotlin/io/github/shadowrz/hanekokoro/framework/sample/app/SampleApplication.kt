package io.github.shadowrz.hanekokoro.framework.sample.app

import android.app.Application
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication
import io.github.shadowrz.hanekokoro.framework.sample.app.di.AppGraph

class SampleApplication :
    Application(),
    MetroApplication {
    private val appGraph by lazy { createGraph<AppGraph>() }

    override val appComponentProviders: MetroAppComponentProviders
        get() = appGraph
}
