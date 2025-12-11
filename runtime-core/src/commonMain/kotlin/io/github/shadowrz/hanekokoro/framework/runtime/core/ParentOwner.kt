package io.github.shadowrz.hanekokoro.framework.runtime.core

interface ParentOwner<P> {
    val parent: P? get() = null
}
