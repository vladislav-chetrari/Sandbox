package io.sandbox.app.main.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import io.sandbox.R
import io.sandbox.app.main.MainFragment
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : MainFragment(R.layout.fragment_character_list) {

    private val viewModel by provide<CharacterListViewModel>()
    private val listAdapter = CharacterListAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characters.safeObserve(listAdapter::submitList)
        viewModel.progress.safeObserve { progressBar.isVisible = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = listAdapter
    }
}