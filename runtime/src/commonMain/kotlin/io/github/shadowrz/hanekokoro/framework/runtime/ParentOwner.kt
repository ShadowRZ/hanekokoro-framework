package io.github.shadowrz.hanekokoro.framework.runtime

interface ParentOwner<P> {
    val parent: P? get() = null
}
