package io.sandbox.data.db.dao

import androidx.room.*
import io.sandbox.data.db.entity.Character

@Dao
interface CharacterDao {

    @Query("select * from character where id = :id")
    suspend fun findById(id: String): Character?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(character: Character)
}