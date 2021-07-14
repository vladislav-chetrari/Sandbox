package io.sandbox.data.network.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.sandbox.data.type.CharacterStatus
import io.sandbox.data.type.CharacterStatus.UNKNOWN
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse(
        val id: String = "",
        val name: String = "",
        val status: CharacterStatus = UNKNOWN,
        val species: String = "",
        val gender: String = "",
        val image: String = "",
        @SerializedName("episode")
        val episodes: List<String> = emptyList(),
        val location: NameUrlPairResponse = NameUrlPairResponse()
) : Parcelable