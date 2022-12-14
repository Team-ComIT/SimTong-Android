package com.comit.model

data class HolidayList(
    val holidays: List<Holiday>
) {

    data class Holiday(
        val date: String,
        val title: String,
    )
}
