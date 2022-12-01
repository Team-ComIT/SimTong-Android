package com.comit.domain.usecase

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changeEmail(
            email = params.email,
        )
    }

    data class Params(
        val email: String,
    )
}
