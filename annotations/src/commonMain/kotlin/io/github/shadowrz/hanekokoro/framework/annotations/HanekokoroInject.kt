package io.github.shadowrz.hanekokoro.framework.annotations

import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
public annotation class HanekokoroInject(
    val scope: KClass<*>,
) {
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    public annotation class ContributesComponent(
        val scope: KClass<*>,
    )

    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
    public annotation class ContributesRenderer(
        val scope: KClass<*>,
    )
}
