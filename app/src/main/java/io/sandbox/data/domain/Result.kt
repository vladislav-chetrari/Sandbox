package io.sandbox.data.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import io.sandbox.app.base.liveData
import io.sandbox.app.base.mutable

sealed class Result<out T> {
    data class Success<out R>(val result: R) : Result<R>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Progress : Result<Nothing>()
}

class ResultLiveData<T>(private val raw: LiveData<Result<T>> = liveData()) {
    val progress: LiveData<Boolean> = map(raw) { it is Result.Progress }
    val error: LiveData<Throwable?> = map(raw) { (it as? Result.Error)?.throwable }
    val result: LiveData<T?> = map(raw) { (it as? Result.Success)?.result }

    fun postValue(value: Result<T>) = raw.mutable.postValue(value)
}