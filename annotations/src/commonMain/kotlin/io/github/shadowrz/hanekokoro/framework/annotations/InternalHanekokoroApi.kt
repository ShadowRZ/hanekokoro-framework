package io.github.shadowrz.hanekokoro.framework.annotations

@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message =
        "This API is only intended for internal usage in Hanekokoro Framework. " +
            "Please file an issue, describing why this internal API is required, " +
            "so we can provide a stable API instead.",
)
annotation class InternalHanekokoroApi
