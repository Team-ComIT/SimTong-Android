package com.comit.model

import java.time.LocalDateTime

data class Token(
    val accessToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshToken: String,
)
