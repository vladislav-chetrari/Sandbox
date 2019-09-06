package io.sandbox.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameUrlPair(
        val name: String = "",
        val url: String = ""
) : Parcelable