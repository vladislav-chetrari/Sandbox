package io.sandbox.unsplash.app.datasource

sealed class DataSourceState {
    object Progress : DataSourceState()
    object Success : DataSourceState()
    class Fail(val throwable: Throwable) : DataSourceState()
}