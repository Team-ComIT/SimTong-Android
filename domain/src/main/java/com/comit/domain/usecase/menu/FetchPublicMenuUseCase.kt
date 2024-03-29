package com.comit.domain.usecase.menu

import com.comit.domain.repository.MenuRepository
import java.util.Date
import javax.inject.Inject

class FetchPublicMenuUseCase @Inject constructor(
    private val repository: MenuRepository,
) {

    suspend operator fun invoke(
        date: Date,
    ) = kotlin.runCatching {
        repository.fetchPublicMenu(
            date = date,
        )
    }
}
