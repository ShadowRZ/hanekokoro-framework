# Introduction

!!! warning

    At this time, this project doesn't have a very stable API yet
    as the project iterates its design.

    The docs are also WIP, you might want to refer to
    [API docs](api/) currently.

    **Signifient changes may happen at any time.**

!!! warning "Documentation is for unreleased code"

    Consult your local artifact for released versions.

    This is an temporary limitation.

Hanekokoro Framework is @ShadowRZ's own framework for writing applications.

It is inspired from the architecture of [Element X Android][element-x-android],
and APIs of [Appyx][appyx], [Circuit][circuit],
and [Amazon App Platform][amzn-app-platform].

Built on top of [Decompose][decompose], it serves as an lightweight wrapper
on top of it, along with convenience functions.

[element-x-android]: https://github.com/element-hq/element-x-android
[appyx]: https://bumble-tech.github.io/appyx/
[circuit]: https://slackhq.github.io/circuit/
[amzn-app-platform]: https://amzn.github.io/app-platform/
[decompose]: https://arkivanov.github.io/Decompose/

## Principles

* [Component](component.md) based organization
* Supports writing [Presenters](presenter.md)
