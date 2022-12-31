package io.sandbox.app.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) {
    if (url.isNullOrBlank()) return
    Picasso.get().load(url).into(this)
}