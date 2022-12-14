package com.comit.domain.usecase.commons

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: CommonsRepository
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changePassword(
            password = params.password,
            newPassword = params.newPassword,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }

    data class Params(
        val password: String,
        val newPassword: String,
    )
}
