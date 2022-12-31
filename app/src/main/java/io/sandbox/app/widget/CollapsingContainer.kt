package io.sandbox.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.isVisible
import io.sandbox.R
import io.sandbox.app.extension.rotate180
import io.sandbox.databinding.CollapsingContainerBinding

class CollapsingContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: CollapsingContainerBinding

    init {
        orientation = VERTICAL
        binding = CollapsingContainerBinding.inflate(LayoutInflater.from(context), this, true)
        binding.dropDownIcon.rotate180()
        val clickListener = OnClickListener { swapContentVisibility() }
        binding.header.setOnClickListener(clickListener)
        binding.header.forEach { it.setOnClickListener(clickListener) }
        setupStyledAttributes(attrs)
    }

    private var isCollapsed = false

    private fun swapContentVisibility() {
        forEachIndexed { index, view ->
            if (index != 0) view.isVisible = isCollapsed
        }
        isCollapsed = !isCollapsed
        binding.dropDownIcon.rotate180()
    }

    private fun setupStyledAttributes(attrs: AttributeSet?) =
        with(context.obtainStyledAttributes(attrs, R.styleable.CollapsingContainer)) {
            binding.headerTitle.text = getString(R.styleable.CollapsingContainer_headerText).orEmpty()
            recycle()
        }
}