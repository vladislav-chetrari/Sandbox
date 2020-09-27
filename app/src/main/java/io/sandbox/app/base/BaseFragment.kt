package io.sandbox.app.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId), HasAndroidInjector {

    @Inject
    internal lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector() = injector

    /*
    * provided ViewModel depends on fragment lifecycle!
    * usage:
    * private val viewModel by provide<ExampleViewModel>()
    * or
    * private val viewModel by provide<ExampleViewModel>{ parentFragment!! }
    */
    protected inline fun <reified VM : ViewModel> viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this }
    ) = viewModels<VM>(ownerProducer) { factory }

    /*
    * provided ViewModel depends on parent activity lifecycle!
    * usage:
    * private val viewModel by provideFromActivity<ExampleViewModel>()
    */
    protected inline fun <reified VM : ViewModel> activityViewModels() =
        activityViewModels<VM> { factory }

    protected fun <T> LiveData<T>.observe(consumer: (T) -> Unit) =
            observe(viewLifecycleOwner, Observer { consumer(it) })

    protected fun <T> LiveData<T?>.safeObserve(consumer: (T) -> Unit) =
            observe(viewLifecycleOwner, Observer { it?.let(consumer) })
}