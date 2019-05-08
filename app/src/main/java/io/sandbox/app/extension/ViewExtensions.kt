package io.sandbox.app.extension

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(condition: Boolean?) {
    isVisible = condition ?: false
}

fun View.rotate180() {
    val duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    animate().rotation(if (rotation == 0f) -180f else 0f).setDuration(duration).start()
}