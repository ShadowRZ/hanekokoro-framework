# `Component`

At its core, Hanekokoro Framework is built on a tree of `Component`s.
These are implmented on top of [Decompose Component](https://arkivanov.github.io/Decompose/component/overview/),
providing the following extensions on top of it:

* `HanekokoroContext`: Inspired from [Circuit's `CircuitContext`](https://slackhq.github.io/circuit/api/0.x/circuit-runtime/com.slack.circuit.runtime/-circuit-context/index.html)
but serves a different purpose: it allows field value-like property access extensions.
* `PluginsOwner`: Inspired from [Appyx](https://bumble-tech.github.io/appyx/apps/plugins/).
