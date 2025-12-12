package io.github.shadowrz.hanekokoro.framework.annotations

import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class HanekokoroInject(
    val scope: KClass<*>,
) {
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
    annotation class ContributesComponent(
        val scope: KClass<*>,
    )
}
