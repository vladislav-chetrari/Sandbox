package io.sandbox.app.extension

import android.view.View

fun View.rotate180() {
    val duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    animate().rotation(if (rotation == 0f) -180f else 0f).setDuration(duration).start()
}