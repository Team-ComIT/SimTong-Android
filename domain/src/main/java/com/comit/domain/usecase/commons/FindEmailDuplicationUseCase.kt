package com.comit.domain.usecase.commons

import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class FindEmailDuplicationUseCase @Inject constructor(
    private val repository: CommonsRepository,
) {

    suspend operator fun invoke(
        email: String,
    ) = kotlin.runCatching {
        repository.findEmailDuplication(
            email = email
        )
    }
}
