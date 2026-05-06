# `Presenter`

```kotlin
fun interface Presenter<S : HanekokoroState> {
    @Composable
    fun present(): S
}
```

## Overview

In unidirectional dataflow pattern, `Presenter` encapsules business logic
into a presenter for producing a stream of states.

While presenters presents reactive state value (such as `StateFlow`),
the API interface above  uses a composable function instead.
Compose Runtime allows you to write concise logic returning desired state,
from which it can be observed in Composables, or turn into `StateFlow`[^1].

[^1]: Requires [Molecule](https://github.com/cashapp/molecule).

A presenter can look like this:

```kotlin
class SimplePresenter : Presenter<State> {
    @Composable
    override fun present(): State {
        var showDialog by remember { mutableStateOf(false) }

        return State(showDialog) { event -> ... }
    }
}
```

In the example above, `State` is a `@Stable` class container that is being
presented to others. `present()` function computes a `State` value and
return it. UI events are dispatched through an event sink parameter.

`State` can look like this:

```kotlin
data class State(
    val showDialog: Boolean
)
```

## Back Navigation

The [AndroidX NavigationEvent](https://developer.android.com/jetpack/androidx/releases/navigationevent)
library should be used in presenters for managing back navigation.

Note that **it's important to enable the back handler only when required**, as (like Decompose) the
`LocalNavigationEventDispatcherOwner` isn't propagated.

## State Rentention

!!! warning

    TODO

    Primary concerns about `remember`, `rememberSavable`, and `retain` (since Compose 1.10)
