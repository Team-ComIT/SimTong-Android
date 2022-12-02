package com.comit.remote.response.menu

import com.google.gson.annotations.SerializedName

data class PublicMenuResponse(

    @field:SerializedName("menu")
    val menu: List<Menu>,
) {
    data class Menu(

        @field:SerializedName("date")
        val date: String,

        @field:SerializedName("meal")
        val meal: String,
    )
}
