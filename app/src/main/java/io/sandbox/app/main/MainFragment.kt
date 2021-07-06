package io.sandbox.app.main

import android.os.Bundle
import android.view.View
import io.sandbox.R
import io.sandbox.app.base.BaseActivity
import io.sandbox.app.base.BaseFragment

abstract class MainFragment(contentLayoutId: Int) : BaseFragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as BaseActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
    }
}