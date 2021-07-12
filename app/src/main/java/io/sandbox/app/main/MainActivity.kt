package io.sandbox.app.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.sandbox.R
import io.sandbox.app.base.view.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val navigationController: NavController by lazy {
        Navigation.findNavController(this, R.id.navigationHostFragment)
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()
}