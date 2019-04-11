package io.sandbox.unsplash.app.main.home

import android.os.Bundle
import android.view.View
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private val viewModel by lazy { fromActivity<HomeViewModel>(factory) }
    private val listAdapter = HomeListAdapter()
    override val layoutResId = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.characters.safeObserve(listAdapter::submitList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = listAdapter
    }
}