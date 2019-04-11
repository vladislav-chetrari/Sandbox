package io.sandbox.unsplash.data.model

import com.google.gson.annotations.SerializedName

enum class CharacterStatus {
    @SerializedName("Alive")
    ALIVE,
    @SerializedName("Dead")
    DEAD,
    @SerializedName("unknown")
    UNKNOWN
}