package io.sandbox.unsplash.data

import io.sandbox.unsplash.data.model.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("search")
    fun all(
        @Query("limit") pageSize: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Call<List<Cat>>
}