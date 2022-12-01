package com.comit.remote.mapper

import com.comit.model.Token
import com.comit.remote.response.users.SignInResponse

internal fun SignInResponse.toModel() =
    Token(
        accessToken = accessToken,
        accessTokenExp = accessTokenExp,
        refreshToken = refreshToken,
    )
