package io.sandbox.app.main.routes.other.multitouch

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.app.extension.drawable
import io.sandbox.databinding.FragmentMultitouchBinding

class MultitouchFragment : BaseFragment<FragmentMultitouchBinding>(FragmentMultitouchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.run {
            setupWithNavController(findNavController())
            //fix embedded nav graph not displaying back button on toolbar
            navigationIcon = requireContext().drawable(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }
}