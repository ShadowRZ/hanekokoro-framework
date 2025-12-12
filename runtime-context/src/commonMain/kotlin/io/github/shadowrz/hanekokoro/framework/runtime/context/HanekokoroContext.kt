package io.github.shadowrz.hanekokoro.framework.runtime.context

import androidx.compose.runtime.Stable
import kotlin.reflect.KClass

// Based on Slack's implmentation.

@Stable
class HanekokoroContext internal constructor(
    val parent: HanekokoroContext?,
    private val tags: MutableMap<KClass<*>, Any> = mutableMapOf(),
) {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> tag(klass: KClass<T>): T? = this.tags[klass] as T?

    inline fun <reified T : Any> tag() = tag(klass = T::class)

    fun <T : Any> putTag(
        klass: KClass<T>,
        value: T?,
    ) {
        if (value == null) {
            this.tags.remove(klass)
        } else {
            this.tags[klass] = value
        }
    }

    inline fun <reified T : Any> putTag(value: T?) = putTag(klass = T::class, value = value)

    fun clearTags() {
        this.tags.clear()
    }
}
