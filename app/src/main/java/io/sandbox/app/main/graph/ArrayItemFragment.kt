package io.sandbox.app.main.graph

import android.graphics.Color.BLACK
import android.graphics.Color.BLUE
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Color.WHITE
import android.graphics.Color.YELLOW
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.sandbox.R
import io.sandbox.app.base.BaseActivity
import io.sandbox.app.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_array_item.*

class ArrayItemFragment : BaseFragment() {

    private val args by navArgs<ArrayItemFragmentArgs>()

    override val layoutResId = R.layout.fragment_array_item

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        super.onCreateView(inflater, container, savedInstanceState).apply {
            (activity as? BaseActivity)?.setSupportActionBar(toolbar)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as? FrameLayout)?.setBackgroundColor(bgColors[args.orderNo % bgColors.size])
        toolbar.title = args.orderNo.toString()
        next.setOnClickListener {
            val destination = ArrayItemFragmentDirections.actionFragmentArrayItemSelf(args.orderNo.inc())
            view.findNavController().navigate(destination)
        }
    }

    private companion object {
        val bgColors = listOf(WHITE, RED, YELLOW, GREEN, BLUE, BLACK)
    }
}