package com.comit.domain.usecase.menu

import com.comit.domain.repository.MenuRepository
import java.util.Date
import javax.inject.Inject

class FetchMenuUseCase @Inject constructor(
    private val repository: MenuRepository,
) {

    suspend operator fun invoke(
        date: Date,
    ) = kotlin.runCatching {
        repository.fetchMenu(
            date = date,
        )
    }
}
