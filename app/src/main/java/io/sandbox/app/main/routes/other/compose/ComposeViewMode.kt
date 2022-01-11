package io.sandbox.app.main.routes.other.compose

sealed class ComposeViewMode {
    object Greeting : ComposeViewMode()
    class Timer(val seconds: Int) : ComposeViewMode()
}
