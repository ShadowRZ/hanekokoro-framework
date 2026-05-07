package io.github.shadowrz.hanekokoro.framework.runtime.navigation

import com.arkivanov.decompose.value.Value
import kotlin.reflect.KClass

public interface NavModel<State : Any> : OnNavigateUpCallbackOwner {
    public val children: Value<State>
}
