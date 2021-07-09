package io.sandbox.app.base.view

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity(
    @LayoutRes private val contentResId: Int
) : AppCompatActivity(contentResId) {

    protected fun <T> LiveData<T>.safeObserve(callback: (T) -> Unit) =
        observe(this@BaseActivity, { it?.run(callback) })

    protected fun <T> LiveData<T>.observe(callback: (T?) -> Unit) =
        observe(this@BaseActivity, { callback(it) })
}