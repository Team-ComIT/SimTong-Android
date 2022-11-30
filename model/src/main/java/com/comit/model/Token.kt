package com.comit.model

data class Token(
    val accessToken: String,
    val access_token_exp: String,
    val refresh_token: String,
)
