package io.sandbox.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    val id: String = "",
    @SerializedName("url")
    val imageUrl: String = "",
    val width: Int = 0,
    val height: Int = 0
)