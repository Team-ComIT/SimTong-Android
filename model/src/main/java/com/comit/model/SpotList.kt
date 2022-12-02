package com.comit.model

data class SpotList(
    val spotList: List<Spot>
) {
    data class Spot(
        val id: String,
        val name: String,
        val location: String,
    )
}
