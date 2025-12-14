# `Presenter`

```kotlin
fun interface Presenter<S : HanekokoroState> {
    @Composable
    fun present(): S
}
```

In unidirectional dataflow pattern, `Presenter` encapsules business logic
into a presenter for producing a stream of states.

While presenters presents reactive state value (such as `StateFlow`),
the API interface above  uses a composable function instead.
Compose Runtime allows you to write concise logic returning desired state,
from which it can be observed in Composables, or turn into `StateFlow`[^1].

[^1]: Requires [Molecule](https://github.com/cashapp/molecule).
