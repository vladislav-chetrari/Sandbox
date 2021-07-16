package io.sandbox.di.module

import android.content.Context
import android.hardware.SensorManager
import androidx.core.content.getSystemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SystemServiceModule {

    @Provides
    @Singleton
    fun sensorManager(
        @ApplicationContext context: Context
    ) = context.getSystemService<SensorManager>()!!
}