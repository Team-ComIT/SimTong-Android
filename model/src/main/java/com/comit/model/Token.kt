package com.comit.model

data class Token(
    val accessToken: String,
    val accessTokenExp: String,
    val refreshToken: String,
)
