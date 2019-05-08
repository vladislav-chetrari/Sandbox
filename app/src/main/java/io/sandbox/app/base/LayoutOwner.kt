package io.sandbox.app.base

import androidx.annotation.LayoutRes

internal interface LayoutOwner {
    
    val layoutResId: Int?
        @LayoutRes
        get() = null
}