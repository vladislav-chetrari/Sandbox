package io.sandbox.app.main.routes.other.compose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.databinding.FragmentComposeBinding

@AndroidEntryPoint
class ComposeFragment : BaseFragment<FragmentComposeBinding>(FragmentComposeBinding::inflate) {

    private val viewModel by viewModels<ComposeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
        binding.composeView.setContent { ComposeFragmentLayout(viewModel) }
    }
}