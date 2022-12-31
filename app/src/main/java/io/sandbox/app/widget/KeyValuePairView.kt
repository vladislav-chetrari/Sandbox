package io.sandbox.app.widget

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import io.sandbox.R
import io.sandbox.app.extension.watch
import io.sandbox.databinding.KeyValuePairViewBinding

class KeyValuePairView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = KeyValuePairViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupStyledAttributes(attrs)
    }

    val key: TextView
        get() = binding.keyTextView
    val value: TextView
        get() = binding.valueTextView

    private var watcher: TextWatcher? = null

    override fun onDetachedFromWindow() {
        if (watcher != null) {
            value.removeTextChangedListener(watcher)
            watcher = null
        }
        super.onDetachedFromWindow()
    }

    private fun setupStyledAttributes(attrs: AttributeSet?) =
        with(context.obtainStyledAttributes(attrs, R.styleable.KeyValuePairView)) {
            setupVisibilityBehavior(getBoolean(R.styleable.KeyValuePairView_visibleWhileEmpty, false))
            key.text = getString(R.styleable.KeyValuePairView_keyText) ?: ""
            value.text = getString(R.styleable.KeyValuePairView_valueText) ?: ""
            recycle()
        }

    private fun setupVisibilityBehavior(visibleWhileEmpty: Boolean) {
        if (visibleWhileEmpty) return
        watcher = value.watch { this@KeyValuePairView.isVisible = !it.isNullOrEmpty() }
    }
}