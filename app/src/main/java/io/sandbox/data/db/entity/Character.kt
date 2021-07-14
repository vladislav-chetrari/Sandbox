package io.sandbox.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.sandbox.data.type.CharacterStatus

@Entity
data class Character(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: String,
    val image: String,
//    val episodes: List<String> = emptyList(),
//    val location: NameUrlPairResponse = NameUrlPairResponse()
)