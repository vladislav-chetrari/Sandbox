package io.sandbox.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.sandbox.data.db.converter.CharacterStatusConverter
import io.sandbox.data.db.dao.CharacterDao
import io.sandbox.data.db.entity.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(CharacterStatusConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}