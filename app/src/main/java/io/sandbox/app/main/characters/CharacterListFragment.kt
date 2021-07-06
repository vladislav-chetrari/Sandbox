package io.sandbox.app.main.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.sandbox.R
import io.sandbox.app.main.MainFragment
import io.sandbox.data.model.Character
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.coroutines.flow.collect

class CharacterListFragment : MainFragment(R.layout.fragment_character_list) {

    private val viewModel by viewModels<CharacterListViewModel>()
    private val listAdapter = CharacterListAdapter(::onCharacterSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter.addLoadStateListener(viewModel::onListLoadStateChanged)
        list.adapter = listAdapter
        refreshLayout.setOnRefreshListener { listAdapter.refresh() }
    }

    override suspend fun observeStateFlows() {
        viewModel.characters.collect(listAdapter::submitData)
    }

    override fun observeLiveData() = viewModel.run {
        isRefreshing.observe { refreshLayout.isRefreshing = it }
        isNextPageLoading.observe { progressBar.isVisible = it }
        pageLoadError.observe {
            val errorMessage = it.message ?: getString(R.string.error_generic)
            Snackbar.make(coordinator, errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) { listAdapter.retry() }
                .show()
        }
    }

    override fun onDestroyView() {
        listAdapter.removeLoadStateListener(viewModel::onListLoadStateChanged)
        list.adapter = null
        super.onDestroyView()
    }

    private fun onCharacterSelected(character: Character) {
        findNavController().navigate(CharacterListFragmentDirections.actionMainNavHomeToCharacterFragment(character.id))
    }
}