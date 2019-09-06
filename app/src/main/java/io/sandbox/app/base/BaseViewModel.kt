package io.sandbox.app.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import timber.log.Timber


abstract class BaseViewModel : ViewModel() {

    @CallSuper
    protected open fun onError(error: Throwable?) {
        error?.let { Timber.e(it) }
    }
}