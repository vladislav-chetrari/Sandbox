package io.sandbox.app.main.routes

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.app.main.routes.Route.*
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.fragment_routes.*

class RoutesFragment : BaseFragment(R.layout.fragment_routes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        list.adapter = RouteListAdapter(::onRouteSelected).apply {
            submitList(Route.values().toList())
        }
    }

    override fun onDestroyView() {
        list.adapter = null
        super.onDestroyView()
    }

    private fun onRouteSelected(route: Route) = findNavController().navigate(
        when (route) {
            PAGINATION -> RoutesFragmentDirections.toCharacterListFragment()
            NAVIGATION -> RoutesFragmentDirections.toArrayItemFragment()
            OTHER -> RoutesFragmentDirections.toOtherRoutesFragment()
        }
    )
}