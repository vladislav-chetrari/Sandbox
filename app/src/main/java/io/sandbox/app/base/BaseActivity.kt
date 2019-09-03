package io.sandbox.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity(
        @LayoutRes private val contentResId: Int
) : AppCompatActivity(contentResId), HasSupportFragmentInjector {

    @Inject
    internal lateinit var injector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector() = injector

    protected inline fun <reified VM : ViewModel> provide() = lazily {
        ViewModelProviders.of(this, factory).get(VM::class.java)
    }

    protected fun <T> LiveData<T>.safeObserve(callback: (T) -> Unit) =
            observe(this@BaseActivity, Observer<T> { it?.run(callback) })

    protected fun <T> LiveData<T>.observe(callback: (T?) -> Unit) =
            observe(this@BaseActivity, Observer<T> { callback(it) })
}