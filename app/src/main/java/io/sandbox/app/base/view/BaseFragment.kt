package io.sandbox.app.base.view

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                observeStateFlows()
            }
        }
    }

    protected open fun observeLiveData() = Unit

    protected open suspend fun observeStateFlows() = Unit

    protected fun <T> LiveData<T>.observe(consumer: (T) -> Unit) =
        observe(viewLifecycleOwner, Observer { consumer(it) })

    protected fun <T> LiveData<T?>.safeObserve(consumer: (T) -> Unit) =
        observe(viewLifecycleOwner, Observer { it?.let(consumer) })
}