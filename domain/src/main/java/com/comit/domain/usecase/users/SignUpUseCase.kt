package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import java.io.File
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
            profileImage = params.profileImage,
        )
    }

    data class Params(
        val name: String,
        val employeeNumber: Int,
        val email: String,
        val password: String,
        val nickname: String?,
        val profileImage: File?,
    )
}
