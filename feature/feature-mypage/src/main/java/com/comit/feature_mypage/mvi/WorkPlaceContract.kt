package com.comit.feature_mypage.mvi

import com.comit.model.SpotList

data class FetchWorkPlaceState(
    val spotList: List<Spot> = listOf(),
    val errMsgSpotList: String = "",
) {
    data class Spot(
        val id: String,
        val name: String,
        val location: String,
    )
}

fun SpotList.toState() = FetchWorkPlaceState(
    spotList = spotList.map {
        it.toStateSpot()
    }
)

fun SpotList.Spot.toStateSpot() = FetchWorkPlaceState.Spot(
    id = id,
    name = name,
    location = location
)

sealed class FetchWorkPlaceSideEffect {

    object FetchWorkPlaceFail: FetchWorkPlaceSideEffect()
}