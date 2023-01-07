package io.sandbox.app.base.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        lifecycleScope.launchWhenCreated { observeStateFlows() }
    }

    protected open fun observeLiveData() = Unit

    protected open suspend fun observeStateFlows() = Unit

    protected fun <T> LiveData<T>.observe(consumer: (T) -> Unit) =
        observe(viewLifecycleOwner) { consumer(it) }

    protected fun <T> LiveData<T?>.safeObserve(consumer: (T) -> Unit) =
        observe(viewLifecycleOwner) { it?.let(consumer) }
}