package com.comit.feature_auth.mvi

import com.comit.feature_auth.screen.find.employeeNumber.SpotUiModel

data class FindEmployeeNumState(

    val name: String = "",
    val email: String = "",
    val place: String = "",
    val placeId: String = "",
    val placeList: List<SpotUiModel> = emptyList(),

    val employeeNum: String = "",

    val errMsgName: String? = null,
    val errMsgPlace: String? = null,
    val errMsgEmail: String? = null,
)

sealed class FindEmployeeNumSideEffect {

    data class NavigateToResultScreen(
        val employeeNum: String,
    ) : FindEmployeeNumSideEffect()

    object UserInfoNotMatched : FindEmployeeNumSideEffect()

    object FetchSpot : FindEmployeeNumSideEffect()
}
