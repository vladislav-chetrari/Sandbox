package io.sandbox.app.widget

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import io.sandbox.R
import io.sandbox.app.extension.watch
import kotlinx.android.synthetic.main.key_value_pair_view.view.*

class KeyValuePairView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        inflate(getContext(), R.layout.key_value_pair_view, this)
        setupStyledAttributes(attrs)
    }

    val key: TextView
        get() = keyTextView
    val value: TextView
        get() = valueTextView

    private var watcher: TextWatcher? = null

    override fun onDetachedFromWindow() {
        if (watcher != null) value.removeTextChangedListener(watcher)
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