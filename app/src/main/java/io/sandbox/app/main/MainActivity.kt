package io.sandbox.app.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.sandbox.R
import io.sandbox.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val navigationController: NavController by lazy { findNavController(this, R.id.navigationHostFragment) }

    override val layoutResId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView.setupWithNavController(navigationController)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        toolbar ?: return
        val setupToolbarWithNavController = { toolbar.setupWithNavController(navigationController) }
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) setupToolbarWithNavController()
        else lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                setupToolbarWithNavController()
                owner.lifecycle.removeObserver(this)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()
}
