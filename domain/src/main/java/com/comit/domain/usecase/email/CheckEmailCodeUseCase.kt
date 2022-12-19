package com.comit.domain.usecase.email

import com.comit.domain.repository.EmailRepository
import javax.inject.Inject

class CheckEmailCodeUseCase @Inject constructor(
    private val repository: EmailRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.checkEmailCode(
            email = params.email,
            code = params.code,
        )
    }

    data class Params(
        val email: String,
        val code: String,
    )
}
