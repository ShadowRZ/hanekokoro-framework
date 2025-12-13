package io.github.shadowrz.hanekokoro.framework.runtime

@Deprecated(
    message = "Use io.github.shadowrz.hanekokoro.framework.runtime.component.Component instead",
    replaceWith = ReplaceWith("Component", "io.github.shadowrz.hanekokoro.framework.runtime.component"),
)
typealias Component = io.github.shadowrz.hanekokoro.framework.runtime.component.Component

@Deprecated(
    message = "Use io.github.shadowrz.hanekokoro.framework.runtime.presenter.Presenter instead",
    replaceWith = ReplaceWith("Presenter", "io.github.shadowrz.hanekokoro.framework.runtime.presenter"),
)
typealias Presenter<S> = io.github.shadowrz.hanekokoro.framework.runtime.presenter.Presenter<S>

@Deprecated(
    message = "Use io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin instead",
    replaceWith = ReplaceWith("Plugin", "io.github.shadowrz.hanekokoro.framework.runtime.plugin"),
)
typealias Plugin = io.github.shadowrz.hanekokoro.framework.runtime.plugin.Plugin

@Deprecated(
    message = "Use io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner instead",
    replaceWith = ReplaceWith("PluginsOwner", "io.github.shadowrz.hanekokoro.framework.runtime.plugin"),
)
typealias PluginsOwner = io.github.shadowrz.hanekokoro.framework.runtime.plugin.PluginsOwner
