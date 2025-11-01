package io.github.shadowrz.hanekokoro.framework.runtime

import com.arkivanov.decompose.GenericComponentContext

interface GenericComponent<Ctx : Any> :
    GenericComponentContext<Ctx>,
    ParentOwner<GenericComponent<*>>,
    PluginsOwner {
    interface Factory<Ctx : Any> {
        fun create(
            context: Ctx,
            parent: GenericComponent<*>?,
            plugins: List<Plugin>,
        ): GenericComponent<Ctx>
    }
}
