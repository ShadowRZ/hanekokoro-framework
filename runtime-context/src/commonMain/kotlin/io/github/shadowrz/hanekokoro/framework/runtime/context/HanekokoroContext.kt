package io.github.shadowrz.hanekokoro.framework.runtime.context

import androidx.compose.runtime.Stable
import io.github.shadowrz.hanekokoro.framework.annotations.InternalHanekokoroApi
import kotlin.reflect.KClass

// Based on Slack's implmentation.

@Stable
public class HanekokoroContext
    @InternalHanekokoroApi
    constructor(
        public val parent: HanekokoroContext?,
        private val tags: MutableMap<KClass<*>, Any> = mutableMapOf(),
    ) {
        @Suppress("UNCHECKED_CAST")
        public fun <T : Any> tag(klass: KClass<T>): T? = this.tags[klass] as T?

        public inline fun <reified T : Any> tag(): T? = tag(klass = T::class)

        public fun <T : Any> putTag(
            klass: KClass<T>,
            value: T?,
        ) {
            if (value == null) {
                this.tags.remove(klass)
            } else {
                this.tags[klass] = value
            }
        }

        public inline fun <reified T : Any> putTag(value: T?): Unit = putTag(klass = T::class, value = value)

        public fun clearTags() {
            this.tags.clear()
        }
    }
