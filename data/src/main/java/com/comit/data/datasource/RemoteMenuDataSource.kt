package com.comit.data.datasource

import com.comit.model.MenuList
import java.util.Date

interface RemoteMenuDataSource {

    suspend fun fetchMenu(
        date: Date
    ): MenuList

    suspend fun fetchPublicMenu(
        date: Date
    ): MenuList
}
