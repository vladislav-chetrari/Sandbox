package io.sandbox.unsplash.data

import io.sandbox.unsplash.data.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun characters(@Query("page") page: Int): Call<CharactersResponse>
}