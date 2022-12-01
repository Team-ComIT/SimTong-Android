package com.comit.domain.usecase

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.signIn(
            employeeNumber = params.employeeNumber,
            password = params.password,
        )
    }

    data class Params(
        val employeeNumber: Int,
        val password: String,
    )
}
