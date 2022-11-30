package com.comit.remote.mapper

import com.comit.model.Token
import com.comit.remote.response.users.SignInResponse

internal fun SignInResponse.toModel() =
    Token(
        accessToken = accessToken,
        access_token_exp = accessTokenExp,
        refresh_token = refreshToken,
    )
