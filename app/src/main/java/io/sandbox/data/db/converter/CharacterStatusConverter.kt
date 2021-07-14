package io.sandbox.data.db.converter

import androidx.room.TypeConverter
import io.sandbox.data.type.CharacterStatus

class CharacterStatusConverter {

    @TypeConverter
    fun convertToEntityProperty(databaseValue: String): CharacterStatus = enumValueOf(databaseValue)

    @TypeConverter
    fun convertToDatabaseValue(entityProperty: CharacterStatus): String = entityProperty.name
}
