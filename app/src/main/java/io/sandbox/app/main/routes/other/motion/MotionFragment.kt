package io.sandbox.app.main.routes.other.motion

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.app.base.view.BaseFragment
import io.sandbox.databinding.FragmentMotionBinding

class MotionFragment : BaseFragment<FragmentMotionBinding>(FragmentMotionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarInclude.toolbar.setupWithNavController(findNavController())
    }
}