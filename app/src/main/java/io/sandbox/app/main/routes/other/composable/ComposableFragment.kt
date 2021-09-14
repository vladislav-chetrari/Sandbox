package io.sandbox.app.main.routes.other.composable

import android.os.Bundle
import android.view.View
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_composable.*

class ComposableFragment : Fragment(R.layout.fragment_composable) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        composeView.setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        Text(text = "Hello compose!")
    }
}