package io.sandbox.unsplash.app.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.watch(onTextChanged: (CharSequence?) -> Unit): TextWatcher {
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = onTextChanged(s)
    }
    addTextChangedListener(watcher)
    return watcher
}