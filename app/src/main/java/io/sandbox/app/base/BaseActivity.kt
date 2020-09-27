package io.sandbox.app.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity(
    @LayoutRes private val contentResId: Int
) : AppCompatActivity(contentResId), HasAndroidInjector {

    @Inject
    internal lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector() = injector

    protected inline fun <reified VM : ViewModel> viewModels() = viewModels<VM> { factory }

    protected fun <T> LiveData<T>.safeObserve(callback: (T) -> Unit) =
        observe(this@BaseActivity, Observer<T> { it?.run(callback) })

    protected fun <T> LiveData<T>.observe(callback: (T?) -> Unit) =
        observe(this@BaseActivity, Observer<T> { callback(it) })
}