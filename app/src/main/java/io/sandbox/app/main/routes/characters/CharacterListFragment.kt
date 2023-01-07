package io.sandbox.app.main.routes.characters

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
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.sandbox.R
import io.sandbox.app.base.view.BindingFragment
import io.sandbox.data.network.model.response.CharacterResponse
import io.sandbox.data.type.CharacterStatus
import io.sandbox.databinding.FragmentCharacterListBinding

@AndroidEntryPoint
class CharacterListFragment : BindingFragment<FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val viewModel by viewModels<CharacterListViewModel>()
    private val listAdapter = CharacterListAdapter(::onCharacterSelected)
    private val backPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() = toggleSearchCriteriaLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)
        setupList()
        setupSearch()
    }

    override suspend fun observeStateFlows() {
        viewModel.characters.collect(listAdapter::submitData)
    }

    override fun observeLiveData() = viewModel.run {
        isRefreshing.observe { binding.refreshLayout.isRefreshing = it }
        isNextPageLoading.observe { binding.progressBar.isVisible = it }
        pageLoadError.observe {
            val errorMessage = it.message ?: getString(R.string.error_generic)
            Snackbar.make(binding.coordinator, errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) { listAdapter.retry() }
                .show()
        }
        searchCriteria.observe {
            binding.searchCriteriaName.setText(it.name)
            binding.searchCriteriaStatus.setText(it.status)
            updateSearchByStatusCleanButton()
        }
    }

    override fun onDestroyView() {
        listAdapter.removeLoadStateListener(viewModel::onListLoadStateChanged)
        binding.list.adapter = null
        super.onDestroyView()
    }

    private fun setupSearch() {
        setupSearchByCharacterStatus()
        binding.searchCriteriaOpen.setOnClickListener { toggleSearchCriteriaLayout() }

        binding.searchReset.setOnClickListener {
            viewModel.onSearchReset()
            toggleSearchCriteriaLayout()
        }
        binding.searchCancel.setOnClickListener {
            viewModel.onSearchCancel()
            toggleSearchCriteriaLayout()
        }
        binding.searchConfirm.setOnClickListener {
            viewModel.onSearch(
                binding.searchCriteriaName.text.toString(),
                binding.searchCriteriaStatus.text.toString()
            )
            toggleSearchCriteriaLayout()
        }
    }

    private fun setupSearchByCharacterStatus() {
        binding.searchCriteriaStatus.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_expandable_list_item_1,
                CharacterStatus.values().map(CharacterStatus::stringResId).map(::getString)
            )
        )
        binding.searchCriteriaStatus.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            binding.clearSearchCriteriaStatus.isVisible = true
        }
        binding.clearSearchCriteriaStatus.setOnClickListener {
            binding.searchCriteriaStatus.setText("")
            updateSearchByStatusCleanButton()
        }
    }

    private fun setupList() {
        listAdapter.addLoadStateListener(viewModel::onListLoadStateChanged)
        binding.list.adapter = listAdapter
        binding.refreshLayout.setOnRefreshListener { listAdapter.refresh() }
    }

    private fun onCharacterSelected(character: CharacterResponse) {
        findNavController().navigate(CharacterListFragmentDirections.navigateToCharacterDetails(character.id))
    }

    private fun toggleSearchCriteriaLayout() {
        hideKeyboard()
        binding.searchCriteriaOpen.isVisible = !binding.searchCriteriaOpen.isVisible
        binding.searchCriteria.isVisible = !binding.searchCriteria.isVisible
        backPressedCallback.isEnabled = binding.searchCriteria.isVisible
    }

    private fun updateSearchByStatusCleanButton() {
        binding.clearSearchCriteriaStatus.isVisible = binding.searchCriteriaStatus.text.isNotEmpty()
    }

    private fun hideKeyboard() {
        requireContext().getSystemService<InputMethodManager>()
            ?.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}