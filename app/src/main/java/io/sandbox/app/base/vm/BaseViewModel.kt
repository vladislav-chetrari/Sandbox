package io.sandbox.app.base.vm

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val <T> LiveData<T>.mutable: MutableLiveData<T>
        get() = this as MutableLiveData<T>

    protected fun <T> mutableLiveData(): LiveData<T> = MutableLiveData()

    protected fun <T> mutableLiveData(initialValue: T): LiveData<T> = MutableLiveData(initialValue)

    protected val <T> LiveData<T>.mediator: MediatorLiveData<T>
        get() = this as MediatorLiveData<T>

    protected fun <T> mediatorLiveData(): LiveData<T> = MediatorLiveData()

    protected fun <T> actionLiveData(): LiveData<T> = object : MutableLiveData<T>() {

        @MainThread
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) = super.observe(owner, Observer {
            if (it == null) return@Observer
            observer.onChanged(it)
            value = null
        })
    }

    protected fun <T> actionLiveData(source: LiveData<T>) = object : MediatorLiveData<T>() {

        init {
            addSource(source, ::postValue)
        }

        @MainThread
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) = super.observe(owner, Observer {
            if (it == null) return@Observer
            observer.onChanged(it)
            value = null
        })
    }

    @CallSuper
    protected open fun onError(error: Throwable?) {
        error?.let { Timber.e(it) }
    }

    protected fun launch(context: CoroutineContext = EmptyCoroutineContext, work: suspend () -> Unit) {
        viewModelScope.launch(context) { work() }
    }
}