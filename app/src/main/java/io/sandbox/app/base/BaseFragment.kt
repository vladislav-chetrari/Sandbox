package io.sandbox.app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), LayoutOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            layoutResId?.let { inflater.inflate(it, container, false) }

    protected inline fun <reified VM : ViewModel> Fragment.fromFragment(
            provider: ViewModelProvider.Factory,
            fragment: Fragment = this
    ) = ViewModelProviders.of(fragment, provider).get(VM::class.java)

    protected inline fun <reified VM : ViewModel> Fragment.fromActivity(provider: ViewModelProvider.Factory) =
            ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

    protected fun <T> LiveData<T>.safeObserve(callback: (T) -> Unit) =
            observe(this@BaseFragment, Observer<T> { it?.run(callback) })

    protected fun <T> LiveData<T>.observe(callback: (T?) -> Unit) =
            observe(this@BaseFragment, Observer<T> { callback(it) })
}