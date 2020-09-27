package io.sandbox.app.main.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import io.sandbox.R
import io.sandbox.app.base.lazily
import io.sandbox.app.main.MainFragment
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : MainFragment(R.layout.fragment_character_list) {

    private val viewModel by viewModels<CharactersViewModel>()
    private val listAdapter by lazily { CharacterListAdapter(viewModel::onCharacterSelected) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.characters.observe(listAdapter::submitList)
        viewModel.progress.observe { progressBar.isVisible = it }
        viewModel.character.safeObserve {
            val destination = CharacterListFragmentDirections.actionMainNavHomeToCharacterFragment(it)
            findNavController().navigate(destination)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = listAdapter
    }
}