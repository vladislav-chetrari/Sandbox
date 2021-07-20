package io.sandbox.app.extension

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

fun Context.drawable(@DrawableRes drawableResId: Int) =
    AppCompatResources.getDrawable(this, drawableResId)

fun Context.color(@ColorRes colorResId: Int, theme: Resources.Theme? = null) =
    resources.color(colorResId, theme)

fun Resources.color(@ColorRes colorResId: Int, theme: Resources.Theme? = null) =
    ResourcesCompat.getColor(this, colorResId, theme)