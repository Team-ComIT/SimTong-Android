package com.comit.domain.usecase.menu

import com.comit.domain.model.MealEntity
import com.comit.domain.repository.MenuRepository
import com.comit.model.MenuList
import javax.inject.Inject

class FetchMenuUseCase @Inject constructor(
    private val repository: MenuRepository,
) {

    suspend operator fun invoke(
        startAt: String,
        endAt: String,
    ) = kotlin.runCatching {
        repository.fetchMenu(
            startAt = startAt,
            endAt = endAt,
        ).menu.map {
            it.toEntity()
        }
    }
}

fun MenuList.Menu.toEntity() =
    MealEntity(
        date = date,
        food = meal.split(',')
    )
