package io.sandbox.unsplash.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.isVisible
import io.sandbox.unsplash.R
import io.sandbox.unsplash.app.extension.rotate180
import kotlinx.android.synthetic.main.collapsing_container.view.*

class CollapsingContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        inflate(context, R.layout.collapsing_container, this)
        dropDownIcon.rotate180()
        val clickListener = OnClickListener { swapContentVisibility() }
        header.setOnClickListener(clickListener)
        header.forEach { it.setOnClickListener(clickListener) }
        setupStyledAttributes(attrs)
    }

    private var isCollapsed = false

    private fun swapContentVisibility() {
        forEachIndexed { index, view ->
            if (index != 0) view.isVisible = isCollapsed
        }
        isCollapsed = !isCollapsed
        dropDownIcon.rotate180()
    }

    private fun setupStyledAttributes(attrs: AttributeSet?) =
        with(context.obtainStyledAttributes(attrs, R.styleable.CollapsingContainer)) {
            headerTitle.text = getString(R.styleable.CollapsingContainer_headerText) ?: ""
            recycle()
        }
}