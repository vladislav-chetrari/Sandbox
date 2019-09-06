package io.sandbox.app.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment(
        @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId), HasSupportFragmentInjector {

    @Inject
    internal lateinit var injector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector() = injector

    /*
    * provided ViewModel depends on fragment lifecycle!
    * usage:
    * private val viewModel by provide<ExampleViewModel>()
    * or
    * private val viewModel by provide<ExampleViewModel>{ parentFragment!! }
    */
    protected inline fun <reified VM : ViewModel> provide(crossinline fragment: () -> Fragment = { this }) = lazily {
        ViewModelProviders.of(fragment(), factory).get(VM::class.java)
    }

    /*
    * provided ViewModel depends on parent activity lifecycle!
    * usage:
    * private val viewModel by provideFromActivity<ExampleViewModel>()
    */
    protected inline fun <reified VM : ViewModel> provideFromActivity() = lazily {
        ViewModelProviders.of(requireActivity(), factory).get(VM::class.java)
    }

    protected fun <T> LiveData<T>.observe(consumer: (T) -> Unit) =
            observe(viewLifecycleOwner, Observer { consumer(it) })

    protected fun <T> LiveData<T?>.safeObserve(consumer: (T) -> Unit) =
            observe(viewLifecycleOwner, Observer { it?.let(consumer) })
}