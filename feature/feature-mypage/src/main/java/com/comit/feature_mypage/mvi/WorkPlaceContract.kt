package com.comit.feature_mypage.mvi

import com.comit.model.SpotList
import java.util.UUID

data class FixWorkPlaceState(
    val spotList: List<Spot> = listOf(),
    val spotId: UUID ? = null,

    val errMsgSpotList: String = "근무지점을 불러오는데 실패했습니다.",
) {
    data class Spot(
        val id: String,
        val name: String,
        val location: String,
    )
}

fun SpotList.toState() = FixWorkPlaceState(
    spotList = spotList.map {
        it.toStateSpot()
    }
)

fun SpotList.Spot.toStateSpot() = FixWorkPlaceState.Spot(
    id = id,
    name = name,
    location = location
)

sealed class FixWorkPlaceSideEffect {

    object ChangeWorkPlaceSuccess : FixWorkPlaceSideEffect()

    object NoIdException : FixWorkPlaceSideEffect()

    object TokenException : FixWorkPlaceSideEffect()

    object NotFoundPlaceException : FixWorkPlaceSideEffect()

    object CannotChangePlaceTooMuch : FixWorkPlaceSideEffect()

    object FetchWorkPlaceFail : FixWorkPlaceSideEffect()
}
