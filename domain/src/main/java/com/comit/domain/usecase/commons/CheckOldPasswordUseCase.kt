package com.comit.domain.usecase.commons

import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class CheckOldPasswordUseCase @Inject constructor(
    private val repository: CommonsRepository
) {
    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.checkOldPassword(
            oldPassword = params.oldPassword,
        )
    }

    data class Params(
        val oldPassword: String,
    )
}
