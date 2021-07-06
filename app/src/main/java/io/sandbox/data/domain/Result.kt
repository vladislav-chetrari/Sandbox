package io.sandbox.data.domain

sealed class Result<out T> {
    data class Success<out R>(val result: R) : Result<R>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Progress : Result<Nothing>()
}