package io.sandbox.app.base

import androidx.annotation.MainThread
import androidx.lifecycle.*

val <T> LiveData<T>.mutable: MutableLiveData<T>
    get() = this as MutableLiveData<T>

fun <T> liveData(): LiveData<T> = MutableLiveData<T>()

fun <T> liveData(initialValue: T): LiveData<T> = MutableLiveData<T>(initialValue)

fun <T> actionLiveData(): LiveData<T> = object : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) = super.observe(owner, Observer {
        if (it == null) return@Observer
        observer.onChanged(it)
        value = null
    })
}

fun <T> actionLiveData(source: LiveData<T>) = object : MediatorLiveData<T>() {

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
