package com.comit.remote.datasource

import com.comit.data.datasource.RemoteMenuDataSource
import com.comit.model.MenuList
import com.comit.remote.api.MenuAPI
import com.comit.remote.mapper.toModel
import java.util.Date
import javax.inject.Inject

class RemoteMenuDataSourceImpl @Inject constructor(
    private val menuAPI: MenuAPI,
) : RemoteMenuDataSource {

    override suspend fun fetchMenu(
        date: Date,
    ): MenuList {
        return menuAPI.fetchMenu(
            date = date,
        ).toModel()
    }

    override suspend fun fetchPublicMenu(
        date: Date,
    ): MenuList {
        return menuAPI.fetchPublicMenu(
            date = date,
        ).toModel()
    }
}
