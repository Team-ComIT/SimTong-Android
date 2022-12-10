package com.comit.remote.datasource

import com.comit.data.datasource.RemoteMenuDataSource
import com.comit.data.util.simTongApiCall
import com.comit.model.MenuList
import com.comit.remote.api.MenuAPI
import com.comit.remote.mapper.toModel
import java.util.Date
import javax.inject.Inject

class RemoteMenuDataSourceImpl @Inject constructor(
    private val menuAPI: MenuAPI,
) : RemoteMenuDataSource {

    override suspend fun fetchMenu(
        date: String,
    ): MenuList = simTongApiCall {
        menuAPI.fetchMenu(
            date = date,
        ).toModel()
    }

    override suspend fun fetchPublicMenu(
        date: Date,
    ): MenuList = simTongApiCall {
        menuAPI.fetchPublicMenu(
            date = date,
        ).toModel()
    }
}
