package com.comit.domain.usecase

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.signUp(
            name = params.name,
            employeeNumber = params.employeeNumber,
            email = params.email,
            password = params.password,
            nickname = params.nickname,
            profileImagePath = params.profileImagePath,
        )
    }

    data class Params(
        val name: String,
        val employeeNumber: Int,
        val email: String,
        val password: String,
        val nickname: String?,
        val profileImagePath: String?,
    )
}
