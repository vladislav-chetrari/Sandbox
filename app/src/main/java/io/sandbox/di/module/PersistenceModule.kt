package io.sandbox.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.sandbox.data.db.AppDatabase
import io.sandbox.data.db.dao.CharacterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun roomDb(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "rick-and-morty-db")
        .build()

    @Provides
    @Singleton
    fun characterDao(db: AppDatabase): CharacterDao = db.characterDao()
}
