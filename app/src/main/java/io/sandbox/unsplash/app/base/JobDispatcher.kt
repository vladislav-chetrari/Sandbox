package io.sandbox.unsplash.app.base

import kotlinx.coroutines.Dispatchers

open class JobDispatcher {
    open fun main() = Dispatchers.Main
    open fun io() = Dispatchers.IO
}