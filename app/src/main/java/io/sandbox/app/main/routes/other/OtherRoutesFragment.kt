package io.sandbox.app.main.routes.other

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.databinding.FragmentOtherRoutesBinding

class OtherRoutesFragment : BaseFragment<FragmentOtherRoutesBinding>(FragmentOtherRoutesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view.findViewById(R.id.childHostFragment))
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}