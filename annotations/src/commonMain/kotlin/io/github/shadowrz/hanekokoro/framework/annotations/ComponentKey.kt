package io.github.shadowrz.hanekokoro.framework.annotations

import dev.zacsweers.metro.MapKey
import io.github.shadowrz.hanekokoro.framework.runtime.component.Component
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE,
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ComponentKey(
    val value: KClass<out Component>,
)
