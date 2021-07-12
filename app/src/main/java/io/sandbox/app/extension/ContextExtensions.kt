package io.sandbox.app.extension

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun Context.drawable(@DrawableRes drawableResId: Int) =
    AppCompatResources.getDrawable(this, drawableResId)