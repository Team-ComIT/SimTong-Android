package com.comit.feature_auth.screen.find.employeeNumber

import com.comit.model.SpotList

class SpotUiModel(
    val id: String,
    val name: String,
    val location: String,
)

fun SpotList.Spot.toUiModel() =
    SpotUiModel(
        id = id,
        name = name,
        location = location,
    )

fun SpotList.toUiModel(): List<SpotUiModel> =
    this.spotList.map { it.toUiModel() }