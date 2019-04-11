package io.sandbox.unsplash.data.model

import io.sandbox.unsplash.data.model.CharacterStatus.UNKNOWN

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: CharacterStatus = UNKNOWN,
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val image: String = "",
    val location: NameUrlPair = NameUrlPair()
)