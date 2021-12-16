package io.sandbox.app.main.routes.other.compose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_compose.*

class ComposeFragment : BaseFragment(R.layout.fragment_compose) {

    private val viewModel by viewModels<ComposeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView.setContent { ComposeFragmentLayout(viewModel) }
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
    }
}