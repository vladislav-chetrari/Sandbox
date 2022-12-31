package io.sandbox.app.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

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