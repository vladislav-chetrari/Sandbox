package io.sandbox.unsplash.app.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), LayoutOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        layoutResId?.let(::setContentView)
    }

    protected inline fun <reified VM : ViewModel> FragmentActivity.from(provider: ViewModelProvider.Factory) =
        ViewModelProviders.of(this, provider).get(VM::class.java)

    protected fun <T> LiveData<T>.safeObserve(callback: (T) -> Unit) =
        observe(this@BaseActivity, Observer<T> { it?.run(callback) })

    protected fun <T> LiveData<T>.observe(callback: (T?) -> Unit) =
        observe(this@BaseActivity, Observer<T> { callback(it) })
}