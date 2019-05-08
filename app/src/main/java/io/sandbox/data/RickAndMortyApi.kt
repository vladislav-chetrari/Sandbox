package io.sandbox.data

import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun characters(@Query("page") page: Int): Call<CharactersResponse>

    @GET("character/{characterId}")
    fun character(@Path("characterId") characterId: String): Call<Character>
}