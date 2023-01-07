package io.sandbox.app.main.routes.graph

import android.graphics.Color.*
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import io.sandbox.app.base.view.BindingFragment
import io.sandbox.databinding.FragmentArrayItemBinding

class ArrayItemFragment : BindingFragment<FragmentArrayItemBinding>(FragmentArrayItemBinding::inflate) {

    private val args by navArgs<ArrayItemFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
        binding.appBarInclude.toolbar.title = args.orderNo.toString()
        super.onViewCreated(view, savedInstanceState)
        binding.frameLayout.setBackgroundColor(bgColors[args.orderNo % bgColors.size])
        binding.next.setOnClickListener {
            val destination = ArrayItemFragmentDirections.actionFragmentArrayItemSelf(args.orderNo.inc())
            view.findNavController().navigate(destination)
        }
    }

    private companion object {
        val bgColors = listOf(WHITE, RED, YELLOW, GREEN, BLUE, BLACK)
    }
}