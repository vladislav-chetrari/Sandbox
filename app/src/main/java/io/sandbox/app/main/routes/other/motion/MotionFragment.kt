package io.sandbox.app.main.routes.other.motion

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_character_details.*

class MotionFragment : BaseFragment(R.layout.fragment_motion) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
    }
}