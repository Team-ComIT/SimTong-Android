package com.comit.domain.repository

import com.comit.model.MenuList
import java.util.Date

interface MenuRepository {

    suspend fun fetchMenu(
        startAt: String,
        endAt: String,
    ): MenuList

    suspend fun fetchPublicMenu(
        date: Date
    ): MenuList
}
