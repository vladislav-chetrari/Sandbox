package io.sandbox.data.network.model.response

data class InfoResponse(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)