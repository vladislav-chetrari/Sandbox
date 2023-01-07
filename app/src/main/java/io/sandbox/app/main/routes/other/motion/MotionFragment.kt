package io.sandbox.app.main.routes.other.motion

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.app.base.view.BindingFragment
import io.sandbox.databinding.FragmentMotionBinding

class MotionFragment : BindingFragment<FragmentMotionBinding>(FragmentMotionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
    }
}