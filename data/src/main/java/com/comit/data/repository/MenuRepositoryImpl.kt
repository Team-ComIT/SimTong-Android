package com.comit.data.repository

import com.comit.data.datasource.RemoteMenuDataSource
import com.comit.domain.repository.MenuRepository
import com.comit.model.MenuList
import java.util.Date
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val remoteMenuDataSource: RemoteMenuDataSource,
) : MenuRepository {

    override suspend fun fetchMenu(
        startAt: String,
        endAt: String,
    ): MenuList {
        return remoteMenuDataSource.fetchMenu(
            startAt = startAt,
            endAt = endAt,
        )
    }

    override suspend fun fetchPublicMenu(
        date: Date,
    ): MenuList {
        return remoteMenuDataSource.fetchPublicMenu(
            date = date,
        )
    }
}
