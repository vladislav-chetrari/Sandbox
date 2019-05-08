package io.sandbox.data.model

import com.google.gson.annotations.SerializedName
import io.sandbox.data.model.CharacterStatus.UNKNOWN

data class Character(
    val id: String = "",
    val name: String = "",
    val status: CharacterStatus = UNKNOWN,
    val species: String = "",
    val gender: String = "",
    val image: String = "",
    @SerializedName("episode")
    val episodes: List<String> = emptyList(),
    val location: NameUrlPair = NameUrlPair()
)