package com.comit.domain.usecase.menu

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.model.MealEntity
import com.comit.domain.repository.MenuRepository
import com.comit.model.MenuList
import javax.inject.Inject

class FetchMenuUseCase @Inject constructor(
    private val repository: MenuRepository,
) {

    suspend operator fun invoke(
        date: String,
    ) = kotlin.runCatching {
        repository.fetchMenu(
            date = date,
        ).menu.map {
            it.toEntity()
        }
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}

fun MenuList.Menu.toEntity() =
    MealEntity(
        date = date,
        food = meal.split(',')
    )
