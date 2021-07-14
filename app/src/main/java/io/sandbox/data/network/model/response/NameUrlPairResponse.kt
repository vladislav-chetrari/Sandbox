package io.sandbox.data.network.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameUrlPairResponse(
        val name: String = "",
        val url: String = ""
) : Parcelable