package io.sandbox.app.main.characters

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.sandbox.R
import io.sandbox.app.main.MainFragment
import io.sandbox.data.model.Character
import io.sandbox.data.model.CharacterStatus
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.coroutines.flow.collect


class CharacterListFragment : MainFragment(R.layout.fragment_character_list) {

    private val viewModel by viewModels<CharacterListViewModel>()
    private val listAdapter = CharacterListAdapter(::onCharacterSelected)
    private val backPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() = toggleSearchCriteriaLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)
        setupList()
        setupSearch()
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
        searchCriteria.observe {
            searchCriteriaName.setText(it.name)
            searchCriteriaStatus.setText(it.status)
            updateSearchByStatusCleanButton()
        }
    }

    override fun onDestroyView() {
        listAdapter.removeLoadStateListener(viewModel::onListLoadStateChanged)
        list.adapter = null
        super.onDestroyView()
    }

    private fun setupSearch() {
        setupSearchByCharacterStatus()
        searchCriteriaOpen.setOnClickListener { toggleSearchCriteriaLayout() }

        searchReset.setOnClickListener {
            viewModel.onSearchReset()
            toggleSearchCriteriaLayout()
        }
        searchCancel.setOnClickListener {
            viewModel.onSearchCancel()
            toggleSearchCriteriaLayout()
        }
        searchConfirm.setOnClickListener {
            viewModel.onSearch(
                searchCriteriaName.text.toString(),
                searchCriteriaStatus.text.toString()
            )
            toggleSearchCriteriaLayout()
        }
    }

    private fun setupSearchByCharacterStatus() {
        searchCriteriaStatus.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_expandable_list_item_1,
                CharacterStatus.values().map(CharacterStatus::stringResId).map(::getString)
            )
        )
        searchCriteriaStatus.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            clearSearchCriteriaStatus.isVisible = true
        }
        clearSearchCriteriaStatus.setOnClickListener {
            searchCriteriaStatus.setText("")
            updateSearchByStatusCleanButton()
        }
    }

    private fun setupList() {
        listAdapter.addLoadStateListener(viewModel::onListLoadStateChanged)
        list.adapter = listAdapter
        refreshLayout.setOnRefreshListener { listAdapter.refresh() }
    }

    private fun onCharacterSelected(character: Character) {
        findNavController().navigate(CharacterListFragmentDirections.navigateToCharacterDetails(character.id))
    }

    private fun toggleSearchCriteriaLayout() {
        hideKeyboard()
        searchCriteriaOpen.isVisible = !searchCriteriaOpen.isVisible
        searchCriteria.isVisible = !searchCriteria.isVisible
        backPressedCallback.isEnabled = searchCriteria.isVisible
    }

    private fun updateSearchByStatusCleanButton() {
        clearSearchCriteriaStatus.isVisible = searchCriteriaStatus.text.isNotEmpty()
    }

    private fun hideKeyboard() {
        requireContext().getSystemService<InputMethodManager>()
            ?.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}