package io.sandbox.data.client

import retrofit2.Call

fun <T> Call<T>.body(): T {
    val response = execute()

    if (response.isSuccessful) return response.body()!!
    else throw Throwable(response.errorBody()?.string())
}