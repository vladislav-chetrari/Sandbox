package io.sandbox.data

import io.sandbox.data.model.Character
import io.sandbox.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun characters(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("status") status: String?
    ): CharactersResponse

    @GET("character/{characterId}")
    suspend fun character(
        @Path("characterId") characterId: String
    ): Character
}