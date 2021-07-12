package io.sandbox.app.main.routes.other

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_other_routes.*

class OtherRoutesFragment : BaseFragment(R.layout.fragment_other_routes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view.findViewById(R.id.childHostFragment))
        bottomNavigationView.setupWithNavController(navController)
    }
}