package com.comit.remote.mapper

import com.comit.model.Token
import com.comit.model.User
import com.comit.remote.response.users.FetchUserInformationResponse
import com.comit.remote.response.users.SignInResponse
import com.comit.remote.response.users.SignUpResponse

internal fun SignInResponse.toModel() =
    Token(
        accessToken = accessToken,
        accessTokenExp = accessTokenExp,
        refreshToken = refreshToken,
    )

internal fun SignUpResponse.toModel() =
    Token(
        accessToken = accessToken,
        accessTokenExp = accessTokenExp,
        refreshToken = refreshToken,
    )

internal fun FetchUserInformationResponse.toModel() =
    User(
        name = name,
        email = email,
        nickname = nickname,
        spot = spot,
        profileImagePath = profileImagePath,
    )
