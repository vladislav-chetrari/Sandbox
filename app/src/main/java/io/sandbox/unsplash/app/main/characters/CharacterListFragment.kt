package io.sandbox.unsplash.app.main.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : BaseFragment() {

    private val viewModel by lazy { fromActivity<CharacterListViewModel>(factory) }
    private val listAdapter = CharacterListAdapter()
    override val layoutResId = R.layout.fragment_character_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.characters.safeObserve(listAdapter::submitList)
        viewModel.progress.safeObserve { progressBar.isVisible = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = listAdapter
    }
}