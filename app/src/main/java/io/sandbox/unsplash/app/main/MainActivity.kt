package io.sandbox.unsplash.app.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val navigationController: NavController by lazy { Navigation.findNavController(this, R.id.navigationHostFragment) }

    override val layoutResId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView.setupWithNavController(navigationController)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        toolbar?.setupWithNavController(navigationController)
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()
}
