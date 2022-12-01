package com.comit.remote.response.commons

import com.google.gson.annotations.SerializedName

data class FetchSpotsResponse(
    @SerializedName("spot_list")
    val spotList: List<Spot>
) {
    data class Spot(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("location")
        val location: String,
    )
}
