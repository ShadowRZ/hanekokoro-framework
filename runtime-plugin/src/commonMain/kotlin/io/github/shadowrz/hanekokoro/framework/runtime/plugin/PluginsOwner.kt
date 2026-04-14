package io.github.shadowrz.hanekokoro.framework.runtime.plugin

/**
 * Owner for a list of plugins.
 */
public interface PluginsOwner {
    public val plugins: List<Plugin>
}

public inline fun <reified P : Plugin> PluginsOwner.plugins(): List<P> = this.plugins.filterIsInstance<P>()

public inline fun <reified P : Plugin> PluginsOwner.plugin(): P =
    requireNotNull(maybePlugin<P>()) {
        "Please provide a plugin of type ${P::class.qualifiedName} to component ${this::class.qualifiedName}."
    }

public inline fun <reified P : Plugin> PluginsOwner.maybePlugin(): P? = plugins<P>().firstOrNull()
