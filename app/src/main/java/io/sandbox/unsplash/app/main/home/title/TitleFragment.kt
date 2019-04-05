package io.sandbox.unsplash.app.main.home.title

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_title

    private val args by navArgs<TitleFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = args.title
    }
}