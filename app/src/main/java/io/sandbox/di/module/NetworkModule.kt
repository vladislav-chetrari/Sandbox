package io.sandbox.di.module

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.sandbox.R
import io.sandbox.data.RickAndMortyApi
import io.sandbox.di.ApiBaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @ApiBaseUrl
    fun baseUrl(res: Resources): String = res.getString(R.string.api_base_url)

    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun retrofit(@ApiBaseUrl baseUrl: String, factory: GsonConverterFactory, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    fun rickAndMortyApi(retrofit: Retrofit): RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

    private companion object {
        const val REQUEST_TIMEOUT = 90L
    }
}