package io.sandbox.app.main.graph

import android.graphics.Color.*
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.sandbox.R
import io.sandbox.app.main.MainFragment
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.fragment_array_item.*

class ArrayItemFragment : MainFragment(R.layout.fragment_array_item) {

    private val args by navArgs<ArrayItemFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.title = args.orderNo.toString()
        super.onViewCreated(view, savedInstanceState)
        frameLayout.setBackgroundColor(bgColors[args.orderNo % bgColors.size])
        next.setOnClickListener {
            val destination = ArrayItemFragmentDirections.actionFragmentArrayItemSelf(args.orderNo.inc())
            view.findNavController().navigate(destination)
        }
    }

    private companion object {
        val bgColors = listOf(WHITE, RED, YELLOW, GREEN, BLUE, BLACK)
    }
}