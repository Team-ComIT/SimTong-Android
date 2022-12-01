package com.comit.remote.mapper

import com.comit.model.SpotList
import com.comit.model.Token
import com.comit.model.User
import com.comit.remote.response.commons.FetchSpotsResponse
import com.comit.remote.response.commons.ReissueTokenResponse
import com.comit.remote.response.users.FetchUserInformationResponse
import com.comit.remote.response.users.SignInResponse
import com.comit.remote.response.users.SignUpResponse
import kotlin.math.log

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

internal fun FetchSpotsResponse.toModel(): SpotList {
    fun FetchSpotsResponse.Spot.toModel() =
        SpotList.Spot(
            id = id,
            name = name,
            location = location,
        )

    return SpotList(
        spotList = spotList.map { it.toModel() }
    )
}

internal fun ReissueTokenResponse.toModel() =
    Token(
        accessToken = accessToken,
        accessTokenExp = accessTokenExp,
        refreshToken = refreshToken,
    )
