package io.sandbox.app.main.routes

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.app.main.routes.Route.*
import io.sandbox.databinding.FragmentRoutesBinding

class RoutesFragment : BaseFragment<FragmentRoutesBinding>(FragmentRoutesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
        binding.list.adapter = RouteListAdapter(::onRouteSelected).apply {
            submitList(Route.values().toList())
        }
    }

    override fun onDestroyView() {
        binding.list.adapter = null
        super.onDestroyView()
    }

    private fun onRouteSelected(route: Route) = findNavController().navigate(
        when (route) {
            PAGINATION -> RoutesFragmentDirections.toCharacterListFragment()
            NAVIGATION -> RoutesFragmentDirections.toArrayItemFragment()
            CAMERA -> RoutesFragmentDirections.toCameraFragment()
            SENSORS -> RoutesFragmentDirections.toSensorListFragment()
            OTHER -> RoutesFragmentDirections.toOtherRoutesFragment()
        }
    )
}