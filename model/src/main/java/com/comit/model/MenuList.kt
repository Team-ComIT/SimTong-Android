package com.comit.model

data class MenuList(
    val menu: List<Menu>,
) {
    data class Menu(
        val date: String,
        val meal: String,
    )
}


