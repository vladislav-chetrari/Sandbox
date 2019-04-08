package io.sandbox.unsplash.di.module

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.sandbox.unsplash.R
import io.sandbox.unsplash.data.AuthInterceptor
import io.sandbox.unsplash.data.CatApi
import io.sandbox.unsplash.di.ApiBaseUrl
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
    fun authInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun okHttpClient(authInterceptor: AuthInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun retrofit(@ApiBaseUrl baseUrl: String, factory: GsonConverterFactory, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    fun catApi(retrofit: Retrofit): CatApi = retrofit.create(CatApi::class.java)

    private companion object {
        const val REQUEST_TIMEOUT = 90L
    }
}