package com.comit.remote.mapper

import com.comit.model.MenuList
import com.comit.model.ScheduleList
import com.comit.model.SpotList
import com.comit.model.Token
import com.comit.model.User
import com.comit.remote.response.commons.FetchSpotsResponse
import com.comit.remote.response.commons.ReissueTokenResponse
import com.comit.remote.response.menu.MenuResponse
import com.comit.remote.response.menu.PublicMenuResponse
import com.comit.remote.response.schedules.FetchPersonalScheduleResponse
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

internal fun FetchPersonalScheduleResponse.toModel(): ScheduleList {
    fun FetchPersonalScheduleResponse.Schedule.toModel() =
        ScheduleList.Schedule(
            id = id,
            startAt = startAt,
            endAt = endAt,
            title = title,
        )

    return ScheduleList(
        schedules = schedules.map { it.toModel() }
    )
}

internal fun MenuResponse.toModel(): MenuList {
    fun MenuResponse.Menu.toModel() =
        MenuList.Menu(
            date = date,
            meal = meal,
        )

    return MenuList(
        menu = menu.map { it.toModel() }
    )
}

internal fun PublicMenuResponse.toModel(): MenuList {
    fun PublicMenuResponse.Menu.toModel() =
        MenuList.Menu(
            date = date,
            meal = meal,
        )

    return MenuList(
        menu = menu.map { it.toModel() }
    )
}
